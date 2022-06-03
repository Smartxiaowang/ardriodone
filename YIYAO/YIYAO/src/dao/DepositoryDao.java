package dao;

import bean.DepositoryAndYpBean;

import java.util.List;

public interface DepositoryDao {
    List<DepositoryAndYpBean> select(String txtSearchNumber);

    int insert(DepositoryAndYpBean d);

    int delete(int uId);

    DepositoryAndYpBean selectById(int uId);

    int updById(DepositoryAndYpBean d);
}
