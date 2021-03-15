package com.example.bookalarm.MainPage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookalarm.DB.DBHelper;
import com.example.bookalarm.Data.ListItem;
import com.example.bookalarm.Data.MyListAdapter;
import com.example.bookalarm.R;

import java.util.ArrayList;

public class FragReg extends Fragment {
    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cur;

    private View view;
    private ListView lv1_reg;
    private MyListAdapter myListAdapter;
    private ArrayList<ListItem> listBook;
    private ListItem listItem;
    private ImageButton ib1_reg;
    public static FragReg newInstance(){
        FragReg fragreg = new FragReg();

        return fragreg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_reg, container, false);
        lv1_reg = view.findViewById(R.id.lv1_reg);
        ib1_reg = view.findViewById(R.id.ib1_reg);

        dbHelper = new DBHelper(getActivity(), dbHelper.DATABASE_NAME,null,1);
        db = dbHelper.getReadableDatabase();

        ib1_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur = dbHelper.getAllBookData();
                listBook = new ArrayList<>();
                while(cur.moveToNext()){
                    listItem = new ListItem();
                    listItem.setTitle(cur.getString(1));
                    listItem.setPublisher(cur.getString(2));
                    listItem.setAuthor(cur.getString(3));
                    listItem.setPubDate(cur.getString(4));
                    listItem.setCoverSmallUrl(cur.getString(5));
                    listItem.setPriceStandard(cur.getString(6));
                    listItem.setPriceSales(cur.getString(7));

                    listBook.add(listItem);
                }
                myListAdapter = new MyListAdapter(getActivity(), listBook);
                lv1_reg.setAdapter(myListAdapter);

                lv1_reg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListItem data = listBook.get(position);

                        DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                listBook.remove(position);
                                myListAdapter.notifyDataSetChanged();
                            }
                        };

                        // 삭제를 물어보는 다이얼로그를 생성한다.

                        new AlertDialog.Builder(getActivity()).setTitle("알림").setMessage("해당 도서를 삭제하시겠습니까?").setPositiveButton("삭제", deleteListener).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

                    }

                });
                /*
                lv1_reg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), SearchResult.class);
                        intent.putExtra("obj", listBook.get(position));
                        startActivity(intent);
                    }
                });

                 */
            }
        });
        /*
        dbHelper = new DBHelper(getActivity(), dbHelper.DATABASE_NAME,null,1);
        db = dbHelper.getReadableDatabase();

        cur = db.rawQuery("select * from "+dbHelper.TALBE_NAME,null);

        while(cur.moveToNext()){
            listItem = new ListItem();
            listItem.setTitle(cur.getString(1));
            listItem.setPublisher(cur.getString(2));
            listItem.setAuthor(cur.getString(3));
            listItem.setPubDate(cur.getString(4));
            listItem.setCoverSmallUrl(cur.getString(5));
            listItem.setPriceStandard(cur.getString(6));
            listItem.setPriceSales(cur.getString(7));

            listBook.add(listItem);
        }

        myListAdapter = new MyListAdapter(getActivity(), listBook);
        lv1_reg.setAdapter(myListAdapter);
        */
        return view;

    }
}
