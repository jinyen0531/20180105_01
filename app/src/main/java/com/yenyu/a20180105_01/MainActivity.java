package com.yenyu.a20180105_01;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv,tv2,tv3,tv4;
    EditText ed;
    int ch=-1,tmp=-1;
    boolean []check = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v) //點擊後跳出標題及內容
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("I am a Title"); //建標題
        builder.setMessage("Look at me!!!!!!!"); //建內容
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            //setPositiveButton 正向按鈕 ->確定
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您已確定",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            //setNegativeButton 負向按鈕 ->取消
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您已取消",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("幫助", new DialogInterface.OnClickListener() {
            //setNeutralButton 中立按鈕 ->            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"幫助",Toast.LENGTH_SHORT).show();
            }
        });

        builder.show(); //顯示
    }

    public void click2(View v)
    {
        tv = (TextView)findViewById(R.id.textView);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("TItLE");
        ed = new EditText(MainActivity.this);
        ed.setText(tv.getText().toString()); //EditText顯示未修改前TextView的內容
        builder.setView(ed);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            //setPositiveButton 正向按鈕 ->確定
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv.setText(ed.getText().toString()); //按下確定後將輸入的值顯示在TextView
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            //setNegativeButton 負向按鈕 ->取消
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"您已取消",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("幫助", new DialogInterface.OnClickListener() {
            //setNeutralButton 中立按鈕 ->            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"幫助",Toast.LENGTH_SHORT).show();
            }
        });


        builder.show();

    }

    public void click3(View v) //點擊後顯示列表選項
    {
        tv2= (TextView)findViewById(R.id.textView2);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("列表");
        final String fruits []={"蘋果","香蕉","鳳梨"}; //設一個Array
        builder.setItems(fruits, new DialogInterface.OnClickListener() {
            // setItems 將Array代入  變成可以點擊的列表選項
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               tv2.setText(fruits[i]); //將選擇的選項輸出到TextView
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        //設false 就無法點擊 builder外 離開選項，所以會設一個取消鈕在Builder裡面
        builder.show();


    }

    public void click4(View v) //點擊按鈕後，顯示單選列表
    {
        tv3 = (TextView)findViewById(R.id.textView3);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("單選列表");
        final String fruits[] = {"蘋果","香蕉","鳳梨"};
        tmp=ch; //沒加這行的話，使用者選擇後按取消依舊會記得tmp=i，
                //這樣下一次若直接按確定會跳上一次的tmp=i，所以當取消跳出來時要洗回原本預設
        builder.setSingleChoiceItems(fruits, ch, new DialogInterface.OnClickListener() {
            //利用setSingleChoiceItems(陣列名稱,預設選擇的位置,new...)
            //將預設位置指定一個變數 ch, ch在最上面宣告=-1
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    tmp=i;
                    //在最外面宣告tmp=-1; 當按下選項時，tmp指向按下的序號
                    //使用者未按確認時，不會執行ch=tmp，按下取消的話，會在tmp=i停止
                    //按下取消跳出時，會執行tmp=ch(-1) 變回預設值
            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ch=tmp; //當按下確定的鈕，將ch指向使用者選擇的tmp
                tv3.setText(fruits[ch]); //使用者案確定鈕才會輸出選項
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    public void click5(View v) //點擊後，出現多選列表
    {
        tv4=(TextView) findViewById(R.id.textView4);

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        final String fruits[] = {"蘋果","香蕉","鳳梨","水梨","草莓"};
        builder.setTitle("多選列表");
        builder.setMultiChoiceItems(fruits, check, new DialogInterface.OnMultiChoiceClickListener() {
            @Override //利用setMultiChoiceItems(陣列名稱,宣告在最上面的boolean陣列,new..)
            public void onClick(DialogInterface dialogInterface, int position, boolean b) {
                 StringBuilder sb=new StringBuilder();
                 //多個字串需要不斷累加，會新增StringBuilder的物件來存放
                 for(int i=0;i<=4;i++) //在check迴圈裡面一個一個檢查
                 {
                     if(check[i]) //如果有打勾
                     {
                         sb.append(fruits[i]+","); //就加進去字串
                     }
                 }
                 tv4.setText(sb.toString()); //將sb放進TextView
            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void click6 (View v) //點擊後，顯示自訂Dialog
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("TITLE");
        LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
        //利用LayoutInflater 用來找layout下的佈局文件
        View v1 = inflater.inflate(R.layout.layout1,null);
        // View = inflater.inflate(來源,null)
        // 利用inflate 轉換成View觀看
        final TextView tv =v1.findViewById(R.id.textView7); //定義v1下的子元素
        Button bt=v1.findViewById(R.id.button7);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("helloWorld");
            }
        });
        builder.setView(v1); //在builder的View裡面呼叫v1



        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show(); //顯示
    }

}
