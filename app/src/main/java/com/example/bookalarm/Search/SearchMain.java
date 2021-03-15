package com.example.bookalarm.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookalarm.R;

import org.xmlpull.v1.XmlPullParser;

public class SearchMain extends AppCompatActivity {
    XmlPullParser xpp;
    EditText et_ser;
    ImageButton btn_ser;
    private String key = "B370FE5C1821B1EA3CA3AEFCBDF292D56FE30B022B20FCB7C7856F68AFB7CD9D";
    String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_main);

        et_ser = findViewById(R.id.et_ser);
        btn_ser = findViewById(R.id.btn_ser);

        btn_ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord = et_ser.getText().toString();
                Intent intent = new Intent(SearchMain.this, SearchMore.class);
                intent.putExtra("searchWord",searchWord);
                startActivity(intent);
            }
        });
    }
    /*
    String getXmlData(){
        StringBuffer sb = new StringBuffer();
        searchWord = et_ser.getText().toString();
        String queryUrl = "http://book.interpark.com/api/search.api?key="+key+"&query="+searchWord;

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
                        sb.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("title")){
                            sb.append("제목 : ");
                            xpp.next();
                            sb.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("description")){
                            sb.append("요약 : ");
                            xpp.next();
                            sb.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("pubDate")){
                            sb.append("출간일 :");
                            xpp.next();
                            sb.append(xpp.getText());//description 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("priceStandard")){
                            sb.append("가격 :");
                            xpp.next();
                            sb.append(xpp.getText());//telephone 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("priceSales")){
                            sb.append("할인 가격 :");
                            xpp.next();
                            sb.append(xpp.getText());//address 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("discountRate")){
                            sb.append("할인율 :");
                            xpp.next();
                            sb.append(xpp.getText());//mapx 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("  ,  "); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("saleStatus")){
                            sb.append("재고 :");
                            xpp.next();
                            sb.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("publisher")){
                            sb.append("출판사 :");
                            xpp.next();
                            sb.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("author")){
                            sb.append("작가 :");
                            xpp.next();
                            sb.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("translator")){
                            sb.append("번역가 :");
                            xpp.next();
                            sb.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            sb.append("\n"); //줄바꿈 문자 추가
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) sb.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }
    */
}