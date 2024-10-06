package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    String dataPath = System.getProperty("user.dir") + "//testData//UserData.xlsx";
    XLUtility xl = new XLUtility(dataPath);

    @DataProvider(name = "Data")
    public String[][] getAllAPIData() throws IOException {

        int ttlRow = xl.getRowCount("Sheet1");
        int ttlCell = xl.getCellCount("Sheet1", 1);
        String[][] apiData = new String[ttlRow][ttlCell];
        for (int i = 1; i <= ttlRow; i++) {
            for (int j = 0; j < ttlCell; j++) {
                apiData[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "UserName")
    public String[] getUserName() throws IOException {
        int ttlRow = xl.getRowCount("Sheet1");
//      int ttlCell =xl.getCellCount("Sheet1",1);
        String[] apiData = new String[ttlRow];
        for (int i = 1; i <= ttlRow; i++) {
            apiData[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}
