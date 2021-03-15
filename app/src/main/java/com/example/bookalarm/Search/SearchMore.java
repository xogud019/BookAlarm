package com.example.bookalarm.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookalarm.Data.ListItem;
import com.example.bookalarm.Data.MyListAdapter;
import com.example.bookalarm.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SearchMore extends AppCompatActivity {
    private String key = "B370FE5C1821B1EA3CA3AEFCBDF292D56FE30B022B20FCB7C7856F68AFB7CD9D";
    String searchWord;
    TextView tv_serMo;
    ListView lv_serMo;
    String exep = "인터파크도서검색결과";
    MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_more);

        Intent intent = getIntent();
        searchWord = intent.getStringExtra("searchWord");

        System.out.println("xogud000"+searchWord);
        lv_serMo = findViewById(R.id.lv_serMo);
        tv_serMo = findViewById(R.id.tv_serMo);

        tv_serMo.setText(exep);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ListItem> listItems = getXmlData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myListAdapter = new MyListAdapter(SearchMore.this,listItems);
                        lv_serMo.setAdapter(myListAdapter);

                        lv_serMo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(SearchMore.this, SearchResult.class);
                                intent.putExtra("obj", listItems.get(position));
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    ArrayList<ListItem> getXmlData(){
        String queryUrl = "http://book.interpark.com/api/search.api?key="+key+"&query="+searchWord;
        ListItem li = new ListItem();
        ArrayList<ListItem> list = new ArrayList<>();

        try {
            URL url = new URL(queryUrl);
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType= xpp.getEventType();

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
                            if(xpp.getText().equals(null)){
                                li.setDescription("");
                                break;
                            }
                            li.setDescription("줄거리 :"+xpp.getText());
                        }
                        else if(tag.equals("priceStandard")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setPriceStandard("");
                                break;
                            }
                            li.setPriceStandard("가격 :"+xpp.getText());
                        }
                        else if(tag.equals("priceSales")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setPriceSales("");
                                break;
                            }
                            li.setPriceSales("할인 가격 :"+xpp.getText());
                        }
                        else if(tag.equals("discountRate")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setDiscountRate("");
                                break;
                            }
                            li.setDiscountRate("할인율 :"+xpp.getText());
                        }
                        else if(tag.equals("saleStatus")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setSaleStatus("");
                                break;
                            }
                            li.setSaleStatus("재고 :"+xpp.getText());
                        }
                        else if(tag.equals("publisher")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setPublisher("");
                                break;
                            }
                            li.setPublisher("출판사 :"+xpp.getText());
                        }
                        else if(tag.equals("author")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setAuthor("");
                                break;
                            }
                            li.setAuthor("작가 :"+xpp.getText());
                        }
                        else if(tag.equals("pubDate")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setPubDate("");
                                break;
                            }
                            li.setPubDate("출판일 :"+xpp.getText());
                        }
                        else if(tag.equals("customerReviewRank")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setCustomerReviewRank("");
                                break;
                            }
                            li.setCustomerReviewRank("평정 :"+xpp.getText());
                        }
                        else if(tag.equals("coverSmallUrl")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setCoverSmallUrl("");
                                break;
                            }
                            li.setCoverSmallUrl(xpp.getText());
                        }
                        else if(tag.equals("coverLargeUrl")){
                            xpp.next();
                            if(xpp.getText().equals(null)){
                                li.setCoverLargeUrl("");
                                break;
                            }
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