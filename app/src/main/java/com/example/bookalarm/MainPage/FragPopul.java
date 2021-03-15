package com.example.bookalarm.MainPage;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookalarm.Data.ListItem;
import com.example.bookalarm.Data.MyListAdapter;
import com.example.bookalarm.R;
import com.example.bookalarm.Search.SearchResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class FragPopul extends Fragment {
    private View view;
    XmlPullParser xpp;
    private ListView lv1_pop,lv2_pop;
    MyListAdapter myListAdapter;
    private String exep = "베스트셀러";
    private String key = "B370FE5C1821B1EA3CA3AEFCBDF292D56FE30B022B20FCB7C7856F68AFB7CD9D";
    ImageButton ib1_popul, ib2_popul;


    public static FragPopul newInstance(){
        FragPopul fragpopul = new FragPopul();

        return fragpopul;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.frag_popul, container, false);
        lv1_pop = view.findViewById(R.id.lv1_popu);
        lv2_pop = view.findViewById(R.id.lv2_popu);
        ib1_popul = view.findViewById(R.id.ib1_popul);
        ib2_popul = view.findViewById(R.id.ib2_popul);

        ib1_popul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<ListItem> listItems = getXmlData(100);

                        Log.d("xogud","ik1");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myListAdapter = new MyListAdapter(getActivity(),listItems);
                                lv1_pop.setAdapter(myListAdapter);
                                Log.d("xogud", "ok2");

                                lv1_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), SearchResult.class);
                                        intent.putExtra("obj", listItems.get(position));
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });

        ib2_popul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<ListItem> listItems = getXmlData(200);
                        Log.d("xogud","ik1");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myListAdapter = new MyListAdapter(getActivity(),listItems);
                                lv2_pop.setAdapter(myListAdapter);
                                Log.d("xogud", "ok2");

                                lv2_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), SearchResult.class);
                                        intent.putExtra("obj", listItems.get(position));
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });


        return view;
    }

    ArrayList<ListItem> getXmlData(int id){
        StringBuffer sb = new StringBuffer();
        String queryUrl = "http://book.interpark.com/api/bestSeller.api?key="+key+"&categoryId="+id;
        ListItem li = new ListItem();
        ArrayList<ListItem> list = new ArrayList<>();
        Log.d("xogud","data");

        try {
            URL url = new URL(queryUrl);
            InputStream is= url.openStream(); //url위치로 입력스트림 연결
            Log.d("xogud","connet");
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType= xpp.getEventType();
            Log.d("xogud","indata");

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("title")){
                            Log.d("xogud","in title");

                            xpp.next();
                            if(xpp.getText().equals(exep)) break;
                            if("".equals(xpp.getText())||xpp.getText()==null){
                                li.setTitle("null");
                                break;
                            }
                            li.setTitle("제목 : "+xpp.getText());
                            Log.d("xogud","endTitle");
                        }

                        else if(tag.equals("description")){
                            xpp.next();
                            li.setDescription("줄거리 :"+xpp.getText());
                        }
                        else if(tag.equals("priceStandard")){
                            xpp.next();
                            li.setPriceStandard("가격 :"+xpp.getText());
                        }
                        else if(tag.equals("priceSales")){
                            xpp.next();
                            li.setPriceSales("할인 가격 :"+xpp.getText());
                        }
                        else if(tag.equals("discountRate")){
                            xpp.next();
                            li.setDiscountRate("할인율 :"+xpp.getText());
                        }
                        else if(tag.equals("saleStatus")){
                            xpp.next();
                            li.setSaleStatus("재고 :"+xpp.getText());
                        }
                        else if(tag.equals("publisher")){
                            xpp.next();
                            li.setPublisher("출판사 :"+xpp.getText());
                        }
                        else if(tag.equals("author")){
                            xpp.next();
                            li.setAuthor("작가 :"+xpp.getText());
                        }
                        else if(tag.equals("pubDate")){
                            xpp.next();
                            li.setPubDate("출판일 :"+xpp.getText());
                        }
                        else if(tag.equals("coverSmallUrl")){
                            xpp.next();
                            li.setCoverSmallUrl(xpp.getText());
                        }
                        else if(tag.equals("coverLargeUrl")){
                            xpp.next();
                            li.setCoverLargeUrl(xpp.getText());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")&&li !=null){
                            list.add(li);
                            li = new ListItem();
                            break;
                        }
                }

                eventType= xpp.next();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
