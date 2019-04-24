package db;

import com.hewittj.interview.db.Investor;
import com.hewittj.interview.db.Rep;
import com.hewittj.interview.db.TransactionDataService;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jalisa on 4/23/19.
 */
public class TransactionDataServiceTest {


    TransactionDataService service = new TransactionDataService();


    @Test
    public void testGetData() {

        HashMap<String, Rep> actualMap = service.getData();

        int expectedMapSize = 2;

        assertEquals(actualMap.size(), expectedMapSize);
    }

    @Test
    public void testGetData_TransactionSize() {
        HashMap<String, Rep> actualMap = service.getData();

        int actualSize = actualMap.get("\"Daryl \"\"Moose\"\" Johnston\"").getTransactions().size();
        int expectedSize = 3;

        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testGetData_TransactionSize2() {
        HashMap<String, Rep> actualMap = service.getData();

        int actualSize = actualMap.get("John Q. Public").getTransactions().size();
        int expectedSize = 9;

        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testGetData_InvestorsSize(){
        HashMap<String, Rep> actualMap = service.getData();

        int actualSize = actualMap.get("John Q. Public").getInvestors().size();
        int expectedSize = 2;

        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testGetData_InvestorsSize2() {
        HashMap<String, Rep> actualMap = service.getData();

        int actualSize = actualMap.get("\"Daryl \"\"Moose\"\" Johnston\"").getInvestors().size();
        int expectedSize = 1;

        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testGetData_FundsSize(){
        HashMap<String, Rep> actualMap = service.getData();

        HashMap<String, Investor> investorMap = actualMap.get("John Q. Public").getInvestors();
        investorMap.forEach((name,investor) -> {
            if(name.equals("\"John Doe & Assoc. A Professional Corporation\"")){
                int actualSize = investor.getFunds().size();
                int expectedSize = 2;
                assertEquals(actualSize,expectedSize);
            }else if(name.equals("\"John Doe, Jane Doe JTOWROS\"")){
                int actualSize = investor.getFunds().size();
                int expectedSize = 1;
                assertEquals(actualSize,expectedSize);
            }else{
                fail(name + " Investor should not be in this list");
            }
        });

    }

    @Test
    public void testGetData_FundsSize2(){
        HashMap<String, Rep> actualMap = service.getData();

        HashMap<String, Investor> investorMap = actualMap.get("\"Daryl \"\"Moose\"\" Johnston\"").getInvestors();
        investorMap.forEach((name,investor) -> {
            if(name.equals("Joe Smith")){
                int actualSize = investor.getFunds().size();
                int expectedSize = 1;
                assertEquals(actualSize,expectedSize);
            }else{
                fail(name + " Investor should not be in this list");
            }
        });

    }



}
