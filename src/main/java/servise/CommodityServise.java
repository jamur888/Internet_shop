package servise;

import dao.CommodityDao;
import domain.Commodity;

import java.sql.SQLException;
import java.util.List;

public class CommodityServise {
    private final CommodityDao commodityDao = CommodityDao.getCommodityDao();
    private static CommodityServise commodityServise;

    public CommodityServise() throws SQLException {
    }

    public static CommodityServise getCommodityServise() {
        if (commodityServise == null) {
            try {
                commodityServise = new CommodityServise();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return commodityServise;
    }

    public List<Commodity> getAll() {
        List<Commodity> arrayList = commodityDao.getAll();
        return arrayList;
    }

    public void create(Commodity commodity) {
        if (commodity != null) {
            commodityDao.create(commodity);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
