package com.example.cleanmachine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Integer> count = new MutableLiveData<>();

    public void setCount(Integer totalCount) {
        count.setValue(totalCount);
    }

    public LiveData<Integer> getCount() {
        System.out.println(count);
        return count;
    }

}
