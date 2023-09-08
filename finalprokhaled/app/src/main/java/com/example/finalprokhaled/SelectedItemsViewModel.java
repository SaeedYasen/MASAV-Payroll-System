package com.example.finalprokhaled;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SelectedItemsViewModel extends ViewModel {
    private List<CombinedDetailsModel> selectedItems;

    public List<CombinedDetailsModel> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<CombinedDetailsModel> selectedItems) {
        this.selectedItems = selectedItems;
    }
}
