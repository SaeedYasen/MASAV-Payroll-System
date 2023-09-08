package com.example.finalprokhaled;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class BillingDetailsAdapter extends RecyclerView.Adapter<BillingDetailsAdapter.ViewHolder> {

    private List<CombinedDetailsModel> billingDetailsList;
    Button msav;

    private List<CombinedDetailsModel> selectedItems;


    public List<CombinedDetailsModel> getSelectedItems() {
        return selectedItems;
    }

    public BillingDetailsAdapter(List<CombinedDetailsModel> billingDetailsList) {
        this.billingDetailsList = billingDetailsList;
        this.selectedItems=new ArrayList<>();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CombinedDetailsModel combinedDetails = billingDetailsList.get(position);
        holder.textBillingDate.setText(combinedDetails.getFirstCharge());
        holder.textChargeAmount.setText(combinedDetails.getChargeAmount());
        holder.textCustomerName.setText(combinedDetails.getCustomerName());
        holder.textCustomerId.setText(combinedDetails.getCustomerID());
        holder.textAddress.setText(combinedDetails.getAddress());
        holder.textBankName.setText(combinedDetails.getBankName());
        holder.textBranchCode.setText(combinedDetails.getBranchCode());
        holder.textAccountNumber.setText(combinedDetails.getAccountNumber());
        holder.checkboxSelected.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(combinedDetails);
            } else {
                selectedItems.remove(combinedDetails);
            }
        });




    }

    @Override
    public int getItemCount() {
        return billingDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textBillingDate;
        TextView textChargeAmount;
        TextView textCustomerName;
        TextView textCustomerId;
        TextView textAddress;
        TextView textBankName;
        TextView textBranchCode;
        TextView textAccountNumber;
        CheckBox checkboxSelected;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textBillingDate = itemView.findViewById(R.id.text_billing_date);
            textChargeAmount = itemView.findViewById(R.id.text_charge_amount);
            textCustomerName = itemView.findViewById(R.id.text_customer_name);
            textCustomerId = itemView.findViewById(R.id.text_customer_id);
            textAddress = itemView.findViewById(R.id.text_address);
            textBankName = itemView.findViewById(R.id.text_bank_name);
            textBranchCode = itemView.findViewById(R.id.text_branch_code);
            textAccountNumber = itemView.findViewById(R.id.text_account_number);
            checkboxSelected=itemView.findViewById(R.id.checkbox_selected);
        }
    }
}
