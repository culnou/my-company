package com.culnou.mumu.company.domain.model.common;

import java.util.ArrayList;
import java.util.List;

import com.culnou.mumu.company.domain.model.Indicator;

public class IndicatorSelector {
	
	private static IndicatorSelector selector = null;
	
	private IndicatorSelector() {}
	
	public static IndicatorSelector getIndicatorSelector() {
		if(selector == null) {
			selector = new IndicatorSelector();
			return selector;
		}else {
			return selector;
		}
	}
	
	public  List<Indicator> getIndicators(String indicatorType, String locale){
		 List<Indicator> indicators = new ArrayList<>();
		switch(indicatorType) {
		case "Data":
			if(locale.equals("JP")) {
				Indicator i1 = new Indicator("正確性", "%", "データの正確性");
				i1.setMeasuringMethod("正確性の測定方法");
				i1.setEvaluationStandard("正確性の評価基準");
				indicators.add(i1);
				
				Indicator i2 = new Indicator("完全性", "%", "データの完全性");
				i2.setMeasuringMethod("完全性の測定方法");
				i2.setEvaluationStandard("完全性の評価基準");
				indicators.add(i2);
				
				Indicator i3 = new Indicator("一貫性", "%", "データの一貫性");
				i3.setMeasuringMethod("一貫性の測定方法");
				i3.setEvaluationStandard("一貫性の評価基準");
				indicators.add(i3);
				
				Indicator i4 = new Indicator("一意性", "%", "データの一意性");
				i4.setMeasuringMethod("一意性の測定方法");
				i4.setEvaluationStandard("一意性の評価基準");
				indicators.add(i4);
				
				Indicator i5 = new Indicator("適時性", "%", "データの適時性");
				i5.setMeasuringMethod("適時性の測定方法");
				i5.setEvaluationStandard("適時性の評価基準");
				indicators.add(i5);
				
				Indicator i6 = new Indicator("有効性", "%", "データの有効性");
				i6.setMeasuringMethod("有効性の測定方法");
				i6.setEvaluationStandard("有効性の評価基準");
				indicators.add(i6);
				
				
				
			}else if(locale.equals("US")) {
				
				Indicator i1 = new Indicator("正確性", "%", "データの正確性");
				i1.setMeasuringMethod("正確性の測定方法");
				i1.setEvaluationStandard("正確性の評価基準");
				indicators.add(i1);
				
				Indicator i2 = new Indicator("完全性", "%", "データの完全性");
				i2.setMeasuringMethod("完全性の測定方法");
				i2.setEvaluationStandard("完全性の評価基準");
				indicators.add(i2);
				
				Indicator i3 = new Indicator("一貫性", "%", "データの一貫性");
				i3.setMeasuringMethod("一貫性の測定方法");
				i3.setEvaluationStandard("一貫性の評価基準");
				indicators.add(i3);
				
				Indicator i4 = new Indicator("一意性", "%", "データの一意性");
				i4.setMeasuringMethod("一意性の測定方法");
				i4.setEvaluationStandard("一意性の評価基準");
				indicators.add(i4);
				
				Indicator i5 = new Indicator("適時性", "%", "データの適時性");
				i5.setMeasuringMethod("適時性の測定方法");
				i5.setEvaluationStandard("適時性の評価基準");
				indicators.add(i5);
				
				Indicator i6 = new Indicator("有効性", "%", "データの有効性");
				i6.setMeasuringMethod("有効性の測定方法");
				i6.setEvaluationStandard("有効性の評価基準");
				indicators.add(i6);
				
			}else {
				indicators = null;
			}
			
			
			break;
		case"Customer":
			if(locale.equals("JP")) {
				Indicator i1 = new Indicator("売上", "円", "売上の摘要");
				indicators.add(i1);
				Indicator i2 = new Indicator("ROIC", "円", "ROICの摘要");
				indicators.add(i2);
			}else if(locale.equals("US")) {
				
			}else {
				indicators = null;
			}
			break;
		default:
			indicators = null;
		}
		return indicators;
	}
	

}
