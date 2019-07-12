package com.yulan.service.Impl;

import com.yulan.dao.PaymentBillDao;
import com.yulan.pojo.PaymentBill;
import com.yulan.service.PaymentBillService;
import com.yulan.utils.FileUpload;
import com.yulan.utils.MapUtils;
import com.yulan.utils.Response;
import com.yulan.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    private PaymentBillDao paymentBillDao;


    /**
     * 生成流水号
     * @return
     */
    @Override
    public String getBigPaymentBillId() {
        String id="";
        String idhead="PZ";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s=df.format(new java.util.Date());
        s=s.substring(2);//获取时间后6位，如20190227得190227
        List<Map<String,Object>> idMaps=paymentBillDao.findPaymentBillIds(s);
        if (idMaps.size()==0){
            id=idhead+s+"00001";
        }else {
            List<Integer> nums=new ArrayList<>();
            for (Map<String,Object> map:idMaps){
                String idS=map.get("ID").toString();
                idS=idS.substring(8);//取后面流水号
                int idI=Integer.parseInt(idS);
                nums.add(idI);
            }
            int idIMax= Collections.max(nums);//取最大
            int o=100000;
            o=o+idIMax+1;
            String p=o+"";
            p=p.substring(1);//获取流水号
            id=idhead+s+p;
        }

        return id;
    }

    @Override
    public Map insertPaymentBill(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();
        Timestamp nowTime= new Timestamp(System.currentTimeMillis());//获取当前时间
        Date now=new Date(System.currentTimeMillis());//测试接口

        for (Map.Entry<String, Object> entry : map.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        PaymentBill paymentBill= MapUtils.mapToBean(map,PaymentBill.class);
        paymentBill.setCreateTs(nowTime);
        String id=this.getBigPaymentBillId();
        paymentBill.setId(id);//流水号
        if (paymentBill.getPayDate()==null){
            paymentBill.setPayDate(now);//测试
        }


        if (paymentBillDao.insertPaymentBill(paymentBill)){
            result.put("code",0);
            result.put("msg","新建成功");
            result.put("data",id);
        }else {
            result.put("code",1);
            result.put("msg","新建失败");
        }
        return result;
    }

    @Override
    public Map getPayBills(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();
        String  cid=map.get("cid").toString();
        String  state=map.get("state").toString();
        String beginTime=map.get("beginTime").toString();
        String finishTime=map.get("finishTime").toString();
        Integer limit=(Integer) map.get("limit");
        Integer page=(Integer) map.get("page");
        if (beginTime.equals("") ||finishTime.equals("")){
            beginTime=null;
            finishTime=null;
        }
        if (cid.equals("")){
            cid=null;
        }
        if (state.equals("")){
            state=null;
        }
        Integer lastNum=null;
        if(limit==null||page==null) {
            page=null;
            limit=null;
        } else {
            page=(page-1)*limit+1;
            lastNum=page+limit-1;
        }

        int count =paymentBillDao.countPaybills(cid,state,beginTime,finishTime);

        List<PaymentBill> list=paymentBillDao.getPaymentBillsbyCid(cid,state,beginTime,finishTime,page,lastNum);
        List<PaymentBill> dataList=new ArrayList<>();
        for (PaymentBill pb :list){//转码
            pb.setCname(StringUtil.getUtf8(pb.getCname()));
            pb.setYulanBank(StringUtil.getUtf8(pb.getYulanBank()));
            pb.setPayerName(StringUtil.getUtf8(pb.getPayerName()));
            pb.setMemo(StringUtil.getUtf8(pb.getMemo()));
            pb.setSendbackReason(StringUtil.getUtf8(pb.getSendbackReason()));
            dataList.add(pb);


        }
        result= Response.getResponseMap(0,"SUCCESS",dataList);
        result.put("count",count);





        return result;
    }

    @Override
    public Map getPayBillContent(Map<String, Object> map) throws UnsupportedEncodingException {
        String id=map.get("id").toString();
        Map result=new HashMap();
        PaymentBill paymentBill=paymentBillDao.getPayBillContent(id);

        if(paymentBill==null){//判空
            return Response.getResponseMap(0,"SUCCESS",null);
        }
        //转码
        paymentBill.setCname(StringUtil.getUtf8(paymentBill.getCname()));
        paymentBill.setYulanBank(StringUtil.getUtf8(paymentBill.getYulanBank()));
        paymentBill.setPayerName(StringUtil.getUtf8(paymentBill.getPayerName()));
        paymentBill.setMemo(StringUtil.getUtf8(paymentBill.getMemo()));
        paymentBill.setSendbackReason(StringUtil.getUtf8(paymentBill.getSendbackReason()));

//        String fileName=paymentBill.getImgFileName();
//        String path= FileUpload.getPayBillRealPath(fileName);
//
//        //给前端返回真正的路径
//        paymentBill.setImgFileName(path);
        result=Response.getResponseMap(0,"SUCCESS",paymentBill);


        return result;
    }

    @Override
    public Map updatePayBillState(Map<String, Object> map) {
        String state=map.get("state").toString();
        String id=map.get("id").toString();
        Map result=new HashMap();
        if (paymentBillDao.updatePayBillState(id,state)){
            result=Response.getResponseMap(0,"SUCCESS","OK");
        }else {
            result=Response.getResponseMap(1,"FALSE","FALSE");
        }
        return result;
    }

    @Override
    public Map updatePayBill(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();

        Date now=new Date(System.currentTimeMillis());//测试接口

        for (Map.Entry<String, Object> entry : map.entrySet()) {//转码
            if (entry.getValue() instanceof String) {
                String origin = StringUtil.setUtf8(String.valueOf(entry.getValue()));
                entry.setValue(origin);
            }
        }
        PaymentBill paymentBill= MapUtils.mapToBean(map,PaymentBill.class);


        if (paymentBill.getPayDate()==null){
            paymentBill.setPayDate(now);//测试
        }


        if (paymentBillDao.updatePayBill(paymentBill)){
            result.put("data",paymentBill.getId());
            result.put("code",0);
            result.put("msg","修改成功");
        }else {
            result.put("code",1);
            result.put("msg","修改失败");
        }
        return result;


    }

    @Override
    public Map getPayNameAndAccount(Map<String, Object> map) throws UnsupportedEncodingException {
        Map result=new HashMap();
        List<Map<String,Object>> dataList=new ArrayList<>();
        String cid=map.get("companyId").toString();
        List<Map<String,Object>> list=paymentBillDao.getPayNameAndAccount(cid);
        if (list.size()==0){//无数据返空
            return Response.getResponseMap(0,"SUCCESS",null);
        }
        for (Map<String,Object> map1:list){
            for (Map.Entry<String, Object> entry : map1.entrySet()) {//转码
                if (entry.getValue() instanceof String) {
                    String origin = StringUtil.getUtf8(String.valueOf(entry.getValue()));
                    entry.setValue(origin);
                }
            }
            dataList.add(map1);
        }
        result=Response.getResponseMap(0,"SUCCESS",dataList);

        return result;
    }


}

