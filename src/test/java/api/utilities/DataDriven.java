package api.utilities;
import org.testng.annotations.DataProvider;
public class DataDriven {
        @DataProvider(name="2-D_dp1")
        public Object[][] getData1(){
                Object[][] myData = new Object[1][2];
                myData[0][0]=1;
                myData[0][1]=2;
                return myData;
        }
        @DataProvider(name="2-D_dp2")
        public Object[][] getData2(){
                return new Object[][]{{"A","B",1,2},
                        {"C","D",3,4},
                        {"E","F",5,6},
                        {"G","H",7,8}
                };
        }
}
