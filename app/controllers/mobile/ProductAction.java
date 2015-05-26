package controllers.mobile;

import business.Bid;
import controllers.BaseController;
import play.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: com.shovesoft.sp2p</p>
 * <p>Title: ProductAction.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ProductAction extends BaseController {

    public static void productDetail(){
        if (params.get("bidId") == null) {
            MainContent.moneyMatters();
        }
        Long bidId = Long.valueOf(params.get("bidId"));
        Logger.info(">>bidId:" + bidId);
        Bid bid = new Bid();
        bid.id = bidId;
        Map jsonMap=new HashMap();
        if(null!=bid.repayment_res && bid.repayment_res.length()>44){
            try{
                String project=bid.repayment_res.split(";")[0];
                jsonMap.put("repayment_res_short",project.substring(0,44));//短的资金安全
            }catch (Exception e) {
                jsonMap.put("repayment_res_short",bid.repayment_res.substring(0,44));//短的资金安全
            }
        }else{
            jsonMap.put("repayment_res_short",bid.repayment_res);
        }

        if(null!=bid.description && bid.description.length()>44){
            jsonMap.put("project_introduction_short",bid.description.substring(0,44));
        }else{
            jsonMap.put("project_introduction_short",bid.description);
        }

        Logger.info(">>current bid status:" + bid.status);

        render(bid,jsonMap);
    }
    
    
    public static void productBid(){
    	Bid bid = new Bid();
    	bid.setId(Integer.parseInt(params.get("bidId")));
    	if (bid.id == -1 ) {
    		MainContent.moneyMatters();
    	}
    	
    	ProductAction.render();
    }
}
