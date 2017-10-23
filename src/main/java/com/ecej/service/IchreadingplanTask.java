package com.ecej.service;

import com.ecej.mapper.*;
import com.ecej.po.*;
import com.ecej.utils.RFCUtil;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IchreadingplanTask {
    private static final Logger logger = LoggerFactory.getLogger(IchreadingplanTask.class);

    @Resource
    private ICHCustomerInfoMapper ichCustomerInfoMapper;
    @Resource
    private ICHGasEquiMapper ichGasEquiMapper;
    @Resource
    private ICHInstallInfoMapper ichInstallInfoMapper;
    @Resource
    private ICHReadingPlanMapper ichReadingPlanMapper;
    @Resource
    private MessageInfoMapper messageInfoMapper;
    @Resource
    private ICHPriceMapper ichPriceMapper;

    /**
     * 民用户数据下载
     */
    //@Scheduled(fixedRate = 1 * 60 * 60 * 1000)
    @Scheduled(fixedDelay = 3600000)
    public void saveMeterPlanFromMYH() {
        System.out.println("开始处理sap下载数据");
        JCoFunction func = null;
        Map<String, String> opNums = new HashMap<>();
        Date now = new Date();
        double start_millis = System.currentTimeMillis();
        try {
            // 民用户接口
            func = RFCUtil.getJCoFunction("YCSAPP_METER_INFO_READ");
        } catch (Exception e) {
            System.out.println("获取SAP函数:YCSAPP_METER_INFO_READ失败");
            logger.error("获取SAP函数:YCSAPP_METER_INFO_READ失败", e);
            return;
        }
        if (func == null) {
            return;
        }
        System.out.println(1);
        func.getImportParameterList().setValue("IV_BUKRS", "0571");
        System.out.println(2);
        RFCUtil.execute(func);
        System.out.println(3);
        // 抄表计划table ET_SGSHCBJH
        JCoTable cbjhTable = func.getTableParameterList().getTable("ET_SGSHCBJH");
        System.out.println(4);
        // 抄表计划table ET_EQUI_GS
        JCoTable equiTable = func.getTableParameterList().getTable("ET_EQUI_GS");
        // 主数据table ET_MD_GS(包含了安装信息和BP联系方式信息)
        JCoTable mdTable = func.getTableParameterList().getTable("ET_MD_GS");
        // 安装
        JCoTable installTable = func.getTableParameterList().getTable("ET_DETA_GS");
        // 价格
        JCoTable priceTable = func.getTableParameterList().getTable("ET_YCJTPR_GS");

        System.out.println("cbjhTable的记录数：" + cbjhTable.getNumRows());
        System.out.println("mdTable记录数：" + mdTable.getNumRows());
        System.out.println("priceTable记录数：" + priceTable.getNumRows());
        System.out.println("installTable记录数：" + installTable.getNumRows());
        System.out.println("equiTable记录数：" + equiTable.getNumRows());

        for (int i = 0; i < equiTable.getNumRows(); i++) {
            equiTable.setRow(i);
            // 设备信息
            ICHGasEqui equi = new ICHGasEqui();
            // VSTELLE 类型 VSTELLE CHAR 10 0 房产
            equi.setVstelle(equiTable.getString("VSTELLE"));
            // EQUNR 类型 EQUNR CHAR 18 0 设备编号
            equi.setEqunr(equiTable.getString("EQUNR"));
            // HERST 类型 HERST CHAR 30 0 资产制造商
            equi.setHerst(equiTable.getString("HERST"));
            // EQART 类型 EQART CHAR 10 0 技术对象类型
            equi.setEqart(equiTable.getString("EQART"));
            // TYPBZ 类型 TYPBZ CHAR 20 0 制造商模型号
            equi.setTypbz(equiTable.getString("TYPBZ"));
            // YXATG 类型 YXATG CHAR 1 0 是否新奥提供标志
            equi.setYxatg(equiTable.getString("YXATG"));
            // GWLDT 类型 GWLDT DATS 8 0 担保日期
            equi.setGwldt(equiTable.getString("GWLDT"));
            // GWLEN 类型 GWLEN DATS 8 0 保修结束日期
            equi.setGwlen(equiTable.getString("GWLEN"));
            // ESTAT 类型 J_ESTAT CHAR 5 0 用户状态
            equi.setEstat(equiTable.getString("ESTAT"));
            // ESTXT 类型 J_TXT30 CHAR 30 0 对象状态
            equi.setEstxt(equiTable.getString("ESTXT"));
            // EARTX 类型 EARTX CHAR 20 0 对象文本文本
            equi.setEartx(equiTable.getString("EARTX"));
            // SERGE 类型 SERGE CHAR 30 0 制造商系列号
            equi.setSerge(equiTable.getString("SERGE"));
            // ichGasEquis.add(equi);
            ichGasEquiMapper.insert(equi);
        }

        for (int i = 0; i < mdTable.getNumRows(); i++) {
            mdTable.setRow(i);

            ICHCustomerInfo customerInfo = new ICHCustomerInfo();
            // ANLAGE
            customerInfo.setAnlage(mdTable.getString("ANLAGE"));
            // PARTNER
            customerInfo.setPartner(mdTable.getString("PARTNER"));
            // STREET
            customerInfo.setStreet(mdTable.getString("STREET"));
            // STR_SUPPL1
            customerInfo.setStrSuppl1(mdTable.getString("STR_SUPPL1"));
            // STR_SUPPL2
            customerInfo.setStrSuppl2(mdTable.getString("STR_SUPPL2"));
            // STR_SUPPL3
            customerInfo.setStrSuppl3(mdTable.getString("STR_SUPPL3"));
            // ROOMNUMBER
            customerInfo.setRoomnumber(mdTable.getString("ROOMNUMBER"));
            // NAME_ORG1
            customerInfo.setNameOrg1(mdTable.getString("NAME_ORG1"));
            // TEL_NUMBER1
            customerInfo.setTelNumber1(mdTable.getString("TEL_NUMBER1"));
            // R3_USRER1
            customerInfo.setR3Usrer1(mdTable.getString("R3_USRER1"));
            // CONSNUMBER1
            customerInfo.setConsnumber1(mdTable.getString("CONSNUMBER1"));
            // TEL_NUMBER2
            customerInfo.setTelNumber2(mdTable.getString("TEL_NUMBER2"));
            // R3_USRER2
            customerInfo.setR3Usrer2(mdTable.getString("R3_USRER2"));
            // CONSNUMBER2
            customerInfo.setConsnumber2(mdTable.getString("CONSNUMBER2"));
            // TEL_NUMBER3
            customerInfo.setTelNumber3(mdTable.getString("TEL_NUMBER3"));
            // R3_USRER3
            customerInfo.setR3Usrer3(mdTable.getString("R3_USRER3"));
            // CONSNUMBER3
            customerInfo.setConsnumber3(mdTable.getString("CONSNUMBER3"));
            // TEL_NUMBER4
            customerInfo.setTelNumber4(mdTable.getString("TEL_NUMBER4"));
            // R3_USRER4
            customerInfo.setR3Usrer4(mdTable.getString("R3_USRER4"));
            // TEL_NUMBER5
            customerInfo.setTelNumber5(mdTable.getString("TEL_NUMBER5"));
            // R3_USRER5
            customerInfo.setR3Usrer5(mdTable.getString("R3_USRER5"));
            // VSTELLE
            customerInfo.setVstelle(mdTable.getString("VSTELLE"));
            // TARIFTYP
            customerInfo.setTariftyp(mdTable.getString("TARIFTYP"));
            // BRANCHE
            customerInfo.setBranche(mdTable.getString("BRANCHE"));
            // VERTRAG
            customerInfo.setVertrag(mdTable.getString("VERTRAG"));
            // SERGE
            customerInfo.setSerge(mdTable.getString("SERGE"));
            // BAUFORM
            customerInfo.setBauform(mdTable.getString("BAUFORM"));
            // BAUKLAS
            // customerInfo.setBauklas(mdTable.getString("BAUKLAS"));
            // DEVLOC
            customerInfo.setDevloc(mdTable.getString("DEVLOC"));
            // if(customerInfo.getAnlage()!=null)
            // ichCustomerInfos.put(customerInfo.getAnlage(),customerInfo);
            ichCustomerInfoMapper.insert(customerInfo);
        }

        for (int i = 0; i < installTable.getNumRows(); i++) {
            installTable.setRow(i);

            ICHInstallInfo installInfo = new ICHInstallInfo();
            // VSTELLE
            installInfo.setVstelle(installTable.getString("VSTELLE"));
            // ANLAGE
            installInfo.setAnlage(installTable.getString("ANLAGE"));
            // AB
            installInfo.setBauform(installTable.getString("AB"));
            // ISTYPE
            installInfo.setIstype(installTable.getString("ISTYPE"));
            // BRANCHE
            installInfo.setBranche(installTable.getString("BRANCHE"));
            // TEXT
            installInfo.setText(installTable.getString("TEXT"));
            // SRQUNR
            installInfo.setSrqunr(installTable.getString("SRQUNR"));
            // MATNR
            installInfo.setMatnr(installTable.getString("MATNR"));
            // MAKTX
            installInfo.setMaktx(installTable.getString("MAKTX"));
            // METYP
            installInfo.setMetyp(installTable.getString("METYP"));
            // SERNR
            installInfo.setSernr(installTable.getString("SERNR"));
            // YFHKA
            installInfo.setYfhka(installTable.getString("YFHKA"));
            // YLIUF1
            installInfo.setYliuf1(installTable.getString("YLIUF1"));
            // SERGE
            installInfo.setSerge(installTable.getString("SERGE"));
            // YCDIR
            installInfo.setYcdir(installTable.getString("YCDIR"));
            // GWLDT
            installInfo.setGwldt(installTable.getString("GWLDT"));
            // GWLEN
            installInfo.setGwlen(installTable.getString("GWLEN"));

            // VREFER 类型 E_VREFER CHAR 20 0 遗嘱系统合同编号
            installInfo.setVrefer(installTable.getString("VREFER"));
            // DEVLOC 类型 DEVLOC CHAR 30 0 表计位置
            installInfo.setDevloc(installTable.getString("DEVLOC"));
            // LOCTXT 类型 CHAR 40 0 表计安装位置描述
            installInfo.setLoctxt(installTable.getString("LOCTXT"));
            // YPLACE 类型 YPLACE CHAR 2 0 户内外标志
            installInfo.setYplace(installTable.getString("YPLACE"));
            // PLATXT 类型 CHAR 60 0 户内户外标志描述
            installInfo.setPlatxt(installTable.getString("PLATXT"));
            // VERTRAG 类型 VERTRAG CHAR 10 0 合同
            installInfo.setVertrag(installTable.getString("VERTRAG"));
            // PARTNER 类型 BU_PARTNER CHAR 10 0 业务伙伴编号
            // installInfo.setPartner(installTable.getString("PARTNER"));
            // PARTNER
            // installInfo.setPartner(installTable.getString("PARTNER"));

            // if(installInfo.getAnlage()!=null)
            // ichInstallInfos.put(installInfo.getAnlage(),installInfo);
            installInfo.setBauklas(installTable.getString("BAUKLAS"));
            ichInstallInfoMapper.insert(installInfo);

        }

        for (int i = 0; i < priceTable.getNumRows(); i++) {
            priceTable.setRow(i);
            // 价格
            ICHPrice price = new ICHPrice();
            // // ANLAGE CHAR 10 0 安装
            price.setAnlage(priceTable.getString("ANLAGE"));
            // // TARIFTYP CHAR 10 0 费率类别
            price.setTariftyp(priceTable.getString("TARIFTYP"));
            // // STEP_METHD CHAR 1 0 阶梯执行方式
            price.setStepMethd(priceTable.getString("STEP_METHD"));
            // // BIS DATS 8 0 时段期满日期
            price.setDats(priceTable.getString("BIS"));
            // // XUHAO INT1 3 0 序号
            price.setXuhao(priceTable.getString("XUHAO"));
            // // BUKRS CHAR 4 0 公司代码
            price.setBukrs(priceTable.getString("BUKRS"));
            // // AB DATS 8 0 时间段无效日期
            price.setAb(priceTable.getString("AB"));
            // // JTLEVL CHAR 1 0 阶梯层次
            price.setJtlevl(priceTable.getString("JTLEVL"));
            // // JTBZ CHAR 1 0 普表阶梯标准
            price.setJtbz(priceTable.getString("JTBZ"));
            // // I_ZAHL DEC 12 4 基准气率1
            price.setiZahl(priceTable.getString("I_ZAHL"));
            // // LOWPRICE DEC 12 4 正常价
            price.setLowprice(priceTable.getDouble("LOWPRICE"));
            // // HIGPRICE DEC 12 4 阶梯价1
            price.setHigprice(priceTable.getDouble("HIGPRICE"));
            // // I_ZAHL2 DEC 12 4 基准率2
            price.setiZahl2(priceTable.getDouble("I_ZAHL2"));
            // // MORPRICE DEC 12 4 阶梯价2
            price.setMorprice(priceTable.getDouble("MORPRICE"));
            // // IC_ZAHL DEC 12 4 卡表基准率1
            price.setIcZahl(priceTable.getDouble("IC_ZAHL"));
            // // IC_ZAHL2 DEC 12 4 卡表基准率2
            price.setIcZahl2(priceTable.getDouble("IC_ZAHL2"));
            // // IC_ZAHL3 DEC 12 4 卡表基准率3
            price.setIcZahl3(priceTable.getDouble("IC_ZAHL3"));
            // // ICMON DEC 4 0 卡表阶梯月数
            price.setIcmon(priceTable.getDouble("ICMON"));
            // // I_ZAHL3 DEC 12 4 基准率3
            price.setiZahl3(priceTable.getDouble("I_ZAHL3"));
            // // RESPRICE DEC 12 4 阶梯价3
            price.setResprice(priceTable.getDouble("RESPRICE"));
            // // LAEDA DATS 8 0 上次更改的日期
            price.setLaeda(priceTable.getString("LAEDA"));
            // // AENAM CHAR 12 0 对象更改人员的名称
            price.setAenam(priceTable.getString("AENAM"));
            // // QSYUE CHAR 2 0 起始月
            price.setQsyue(priceTable.getString("QSYUE"));
            // // ZZYUE CHAR 2 0 终止月
            price.setZzyue(priceTable.getString("ZZYUE"));
            // // CACULATETY CHAR 1 0 跨年同年分类
            price.setCaculatety(priceTable.getString("CACULATETY"));

            ichPriceMapper.insert(price);
        }

        System.out.println("ICHPrice");

        int count_readingplan_add = 0;
        int count_readingplan_update = 0;
        for (int i = 0; i < cbjhTable.getNumRows(); i++) {
            cbjhTable.setRow(i);
            ICHReadingPlan plan = null;
            String opnum = cbjhTable.getString("OPNUM");
            ICHReadingPlanExample ichReadingPlanExample = new ICHReadingPlanExample();
            ichReadingPlanExample.createCriteria().andOpnumEqualTo(opnum);
            List<ICHReadingPlan> ichReadingPlans = ichReadingPlanMapper.selectByExample(ichReadingPlanExample);
            if (!ichReadingPlans.isEmpty()) {
                plan = ichReadingPlans.get(0);
            }
            // 数据标识
            String flag = cbjhTable.getString("DATAFLG");
            String pernrOfNow = cbjhTable.getString("PERNR");
            String msg = cbjhTable.getString("OPNUM") + ","
                    + cbjhTable.getString("ANLAGE") + ","
                    + cbjhTable.getString("CBPL") + ","
                    + cbjhTable.getString("ADATSOLL");
            // 如果数据标识或人员编号为空，则忽略该条数据
            if (flag == null || pernrOfNow == null)
                continue;
            // 如果该流水号的抄表计划已经存在
            if (plan != null) {
                // 如果没有换抄表人，只是更改抄表数据，那么生成修改消息
                if (flag.equals("M") && pernrOfNow.equals(plan.getPernr())) {
                    MessageInfo info = new MessageInfo();
                    info.setCreatetime(now);
                    info.setMsgreceiver(pernrOfNow);
                    info.setHasread(0);
                    info.setMsgtype("D002");
                    info.setMsgtypedesc("抄表数据更新");
                    info.setTaskno(msg);
                    messageInfoMapper.insert(info);
                    count_readingplan_update++;
                }
                // 如果删除或者更换了抄表人，那么生成删除原来抄表人的消息
                if (flag.equals("D") || flag.equals("M")
                        && !pernrOfNow.equals(plan.getPernr())) {
                    MessageInfo info = new MessageInfo();
                    info.setCreatetime(now);
                    info.setMsgreceiver(plan.getPernr());
                    info.setHasread(0);
                    info.setMsgtype("D001");
                    info.setMsgtypedesc("抄表数据删除");
                    info.setTaskno(msg);
                    messageInfoMapper.insert(info);
                }
            }
            if (plan == null) {
                plan = new ICHReadingPlan();
                count_readingplan_add++;
            }
            // 抄表计划
            try {
                // BUKRS 类型 BUKRS CHAR 4 0 公司代码
                plan.setBukrs(cbjhTable.getString("BUKRS"));
                // ANLAGE 类型 ANLAGE CHAR 10 0 安装
                plan.setAnlage(cbjhTable.getString("ANLAGE"));
                // TERMSCHL 类型 ABLEINHEIT CHAR 8 0 读表单位
                plan.setTermschl(cbjhTable.getString("TERMSCHL"));
                // ABLESER 类型 ABLESER CHAR 3 0 读表号码
                plan.setAbleser(cbjhTable.getString("ABLESER"));
                // ABLESGR 类型 ABLESGR CHAR 2 0 读表原因
                plan.setAblesgr(cbjhTable.getString("ABLESGR"));
                // TEXT40 类型 TEXT40 CHAR 40 0 读表原因中文描述
                plan.setAblesgre(cbjhTable.getString("TEXT40"));
                // ADATSOLL 类型 ADATSOLL DATS 8 0 计划读表日期
                plan.setAdatsoll(cbjhTable.getString("ADATSOLL"));
                // SCJHRQ 类型 YSCJHRQ DATS 8 0 生成计划日期
                plan.setScjhrq(cbjhTable.getString("SCJHRQ"));
                // SCJHTM 类型 YSCJHTM TIMS 6 0 生成计划时间
                plan.setScjhtm(cbjhTable.getString("SCJHTM"));
                // CBPL 类型 YCBPL DEC 16 0 抄表频率值
                plan.setCbpl(cbjhTable.getString("CBPL"));
                // OPNUM 类型 YOPNUM CHAR 30 0 操作流水号
                plan.setOpnum(cbjhTable.getString("OPNUM"));
                // PERNR 类型 PERNR_D NUMC 8 0 人员编号
                plan.setPernr(cbjhTable.getString("PERNR"));
                // BEGRU 类型 BEGRU CHAR 4 0 权限组
                plan.setBegru(cbjhTable.getString("BEGRU"));
                // CBPLT 类型 YCBPLT CHAR 20 0 抄表频率文本描述
                plan.setCbplt(cbjhTable.getString("CBPLT"));
                // DATAFLG 类型 YDATAFLG CHAR 1 0 数据标识
                plan.setDataflg(cbjhTable.getString("DATAFLG"));
                // ZWNUMMER 类型 E_ZWNUMMER NUMC 3 0 计度器
                plan.setZwnummer(cbjhTable.getString("ZWNUMMER"));
                // V_ZWSTNDAB 类型 V_ZWSTNDAB DEC 17 0 开票读表的小数位前
                plan.setvZwstndab(cbjhTable.getDouble("V_ZWSTNDAB"));
                // N_ZWSTNDAB 类型 N_ZWSTNDAB DEC 14 14 开票读表的小数位
                plan.setnZwstndab(cbjhTable.getDouble("N_ZWSTNDAB"));
                // ADATTATS 类型 ADATTATS DATS 8 0 实际读表日期
                plan.setAdattats(cbjhTable.getString("ADATTATS"));
                // 上次电子表读数
                plan.setLbkdzljll(cbjhTable.getDouble("BKDZLJLL"));
                plan.setExpired("0");
                ichReadingPlanMapper.insert(plan);
                opNums.put(plan.getOpnum(), "S");
            } catch (Exception e) {
                e.printStackTrace();
                opNums.put(plan.getOpnum(), "F");
            }
            // 将成功读取的单号放入LIST，准备返回给SAP
        }
        System.out.println("本次抄表计划数据共计： 新增 " + count_readingplan_add + "(条)    更新 " + count_readingplan_update + "(条)");

        try {
            func = RFCUtil.getJCoFunction("YCSAPP_METER_DATAFLG_CHANGE");
            int size = opNums.size();
            if (size > 0) {
                JCoTable returnTable = func.getTableParameterList().getTable("UPDATFLG");
                returnTable.appendRows(size);
                int i = 0;
                for (Map.Entry<String, String> item : opNums.entrySet()) {
                    returnTable.setRow(i++);
                    returnTable.setValue("OPNUM", item.getKey());
                    returnTable.setValue("DATAFLG", item.getValue());
                }
                RFCUtil.execute(func);
            }
            double end_millis = System.currentTimeMillis();
            System.out.println("<<<<<<<<<" + (end_millis - start_millis) / 1000 + " 秒】 >>>>>>>>>");
        } catch (JCoException e) {
            e.printStackTrace();
        }
        if (func == null)
            return;

        deleteDistinctData();
    }

    /**
     * 删除重复数据
     */
    public void deleteDistinctData() {
        System.out.println("删除重复数据 -- 开始");
        double start_millis = System.currentTimeMillis();
        List<Integer> integers = ichCustomerInfoMapper.selectDistinctData();
        if (integers.size() > 0) {
            ichCustomerInfoMapper.deleteDistinctData(integers);
        }
        List<Integer> integers1 = ichGasEquiMapper.selectDistinctData();
        if (integers1.size() > 0) {
            ichGasEquiMapper.deleteDistinctData(integers1);
        }
        List<Integer> integers2 = ichInstallInfoMapper.selectDistinctData();
        if (integers2.size() > 0) {
            ichInstallInfoMapper.deleteDistinctData(integers2);
        }
        List<Integer> integers3 = ichReadingPlanMapper.selectDistinctData();
        if (integers3.size() > 0) {
            ichReadingPlanMapper.deleteDistinctData(integers3);
        }
        List<Integer> integers4 = ichPriceMapper.selectDistinctData();
        if (integers4.size() > 0) {
            ichPriceMapper.deleteDistinctData(integers4);
        }
        double end_millis = System.currentTimeMillis();
        System.out.println("<<<<<<<<< SAP抄表计划定时作业数据同步,删除重复数据，总耗时【 "
                + (end_millis - start_millis) / 1000 + " 秒】 >>>>>>>>>");
        System.out.println("删除重复数据 -- 结束");

    }
}
