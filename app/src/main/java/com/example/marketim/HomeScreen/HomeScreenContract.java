package com.example.marketim.HomeScreen;

import java.util.List;

public interface HomeScreenContract {

    interface View {
        void getData(List<OrderModel> orders);
    }

    interface Adapter {
        void addList(List<OrderModel> data);
    }

    interface Presenter {
        void getData();

        void deleteRememberMe();
    }
}
