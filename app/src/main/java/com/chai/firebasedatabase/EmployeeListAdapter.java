package com.chai.firebasedatabase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chai.firebasedatabase.models.Employee;

import java.util.List;

/**
 * Created by Rizwan on 9/16/2018.
 */

public class EmployeeListAdapter  extends RecyclerView.Adapter<EmployeeViewHolder>{

    List<Employee> mEmployeeList;
    EmployeeClickListener mListener;

    public EmployeeListAdapter(List<Employee> employees, EmployeeClickListener listener){
        mEmployeeList = employees;
        mListener = listener;
    }
    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee
                ,parent , false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(view,mListener);
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = mEmployeeList.get(position);
        holder.txtName.setText(employee.getName());
        holder.txtAddress.setText(employee.getAddress());
        holder.txtPhone.setText(employee.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    public Employee getItemAtPosition(int position){
        return  mEmployeeList.get(position);
    }
}
