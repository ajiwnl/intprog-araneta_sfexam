package com.ucb.semfinal.araneta_sfexam;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Araneta_ClassSched extends AppCompatActivity {

    ListView listView;

    String day[] = {"Mon", "Wed", "Friday", "Saturday"};
    String time[] = {"10:00 A.M.", "11:00 A.M.", "12:00 P.M.", "1:00 P.M."};

    String code[] = {"123", "456", "789.", "011"};

    String desc[] = {"Description 1", "Description 2", "Description 3", "Description 4"};

    String room[] = {"219", "218", "221", "220"};

    List<String> dayList;
    List<String> timeList;
    List<String> codeList;
    List<String> descList;
    List<String> roomList;

    CustomBaseAdapter customBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_araneta_class_sched);

        listView = findViewById(R.id.customview);

        dayList = new ArrayList<>(Arrays.asList(day));
        timeList = new ArrayList<>(Arrays.asList(time));
        codeList = new ArrayList<>(Arrays.asList(code));
        descList = new ArrayList<>(Arrays.asList(desc));
        roomList = new ArrayList<>(Arrays.asList(room));

        customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), dayList.toArray(new String[0]), timeList.toArray(new String[0]), codeList.toArray(new String[0]), descList.toArray(new String[0]), roomList.toArray(new String[0]));
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Araneta_ClassSched.this, Araneta_ClassSchedDisplay.class);

                // Pass the data of the clicked item as extras in the Intent
                intent.putExtra("day", dayList.get(position));
                intent.putExtra("time", timeList.get(position));
                intent.putExtra("code", codeList.get(position));
                intent.putExtra("desc", descList.get(position));
                intent.putExtra("room", roomList.get(position));

                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Show dialog with edit and delete options
                showDialogWithOptions(position);
                return true;
            }
        });
    }

    private void showDialogWithOptions(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options")
                .setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // Edit option clicked
                                editItem(position);
                                break;
                            case 1:
                                // Delete option clicked
                                deleteItem(position);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void editItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Item");

        // Create a layout inflater to inflate the custom layout for editing
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.edit_item_dialog, null);

        // Get references to EditText views in the dialog layout
        EditText editDay = dialogView.findViewById(R.id.edit_day);
        EditText editTime = dialogView.findViewById(R.id.edit_time);
        EditText editCode = dialogView.findViewById(R.id.edit_code);
        EditText editDesc = dialogView.findViewById(R.id.edit_description);
        EditText editRoom = dialogView.findViewById(R.id.edit_room);

        // Set the current values of the item in the EditText views
        editDay.setText(dayList.get(position));
        editTime.setText(timeList.get(position));
        editCode.setText(codeList.get(position));
        editDesc.setText(descList.get(position));
        editRoom.setText(roomList.get(position));

        builder.setView(dialogView);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dayList.set(position, editDay.getText().toString());
                timeList.set(position, editTime.getText().toString());
                codeList.set(position, editCode.getText().toString());
                descList.set(position, editDesc.getText().toString());
                roomList.set(position, editRoom.getText().toString());


                customBaseAdapter.updateData(dayList, timeList, codeList, descList, roomList);


                Toast.makeText(Araneta_ClassSched.this, "Item edited", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);

        // Show the dialog
        builder.create().show();
    }

    private void deleteItem(int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Check if the position is valid
                if (position >= 0 && position < dayList.size()) {
                    // Delete the item from lists
                    dayList.remove(position);
                    timeList.remove(position);
                    codeList.remove(position);
                    descList.remove(position);
                    roomList.remove(position);

                    // Notify adapter of data change
                    customBaseAdapter.updateData(dayList, timeList, codeList, descList, roomList);

                    Log.d(TAG, "Item at position " + position + " deleted");
                } else {
                    Log.d(TAG, "Invalid position: " + position);
                }
            }
        });
    }
}
