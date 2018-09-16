package com.chai.firebasedatabase.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chai.firebasedatabase.EmployeeClickListener;
import com.chai.firebasedatabase.EmployeeListAdapter;
import com.chai.firebasedatabase.R;
import com.chai.firebasedatabase.models.Employee;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment implements EmployeeClickListener {

    RecyclerView rvEmployees;
    EmployeeListAdapter employeeListAdapter;
    ProgressBar progress;
    // Declare

    //Declare
    List<Employee> employees = new ArrayList<>();

    DatabaseReference mDatabaseReference;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view, container, false);
        rvEmployees = view.findViewById(R.id.rvEmployees);
        progress = view.findViewById(R.id.progress);
        rvEmployees.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("employees");

        employeeListAdapter = new EmployeeListAdapter(employees, this);

        rvEmployees.setAdapter(employeeListAdapter);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                progress.setVisibility(View.GONE);
                Employee employee = dataSnapshot.getValue(Employee.class);
                employees.add(employee);
                employeeListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                employees.remove(employee);
                employeeListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    @Override
    public void onDeleteClick(int position) {
        Employee employee =employees.get(position);
        String id=employee.getId();
        progress.setVisibility(View.VISIBLE);
        mDatabaseReference.child(id).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                progress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {

    }

}
