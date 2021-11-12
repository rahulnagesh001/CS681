package edu.umb.cs681.hw01;
import org.junit.jupiter.api.Test;

import edu.umb.cs681.hw01.DJIAQuoteObservable;
import edu.umb.cs681.hw01.PieChartObserver;
import edu.umb.cs681.hw01.StockQuoteObservable;
import edu.umb.cs681.hw01.TableObserver;
import edu.umb.cs681.hw01.ThreeDObserver;

class ObserverTest {
	
	PieChartObserver PiechartObserver = new PieChartObserver();
	TableObserver TableObserver = new TableObserver();
	ThreeDObserver THREEDObserver = new ThreeDObserver();
	DJIAQuoteObservable djiaObserver = new DJIAQuoteObservable();
	StockQuoteObservable stockObserver = new StockQuoteObservable();
	
		@Test
		void verifyDija() {
			
			djiaObserver.addObserver(PiechartObserver);
			djiaObserver.addObserver(TableObserver);
			djiaObserver.addObserver(THREEDObserver);
			djiaObserver.changeQuote(100);
			djiaObserver.changeQuote(200);
			
		}
		
		@Test
		void verifyStock() {
			
			stockObserver.addObserver(PiechartObserver);
			stockObserver.addObserver(TableObserver);
			stockObserver.addObserver(THREEDObserver);
			stockObserver.changeQuote("rtp", 10);
			stockObserver.changeQuote("rtp1", 27);
		
			
		}
		
	
}
