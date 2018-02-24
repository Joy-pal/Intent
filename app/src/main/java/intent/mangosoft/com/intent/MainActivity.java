package intent.mangosoft.com.intent;

import android.content.Intent;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageButton email,camera,map,market,sdcard;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email=(ImageButton)findViewById(R.id.email);
        camera=(ImageButton)findViewById(R.id.Camera);
        map=(ImageButton)findViewById(R.id.map);
        market=(ImageButton)findViewById(R.id.market);
        sdcard=(ImageButton)findViewById(R.id.sdcard);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String from="joypal.jkp@yahoo.com";
                intent.putExtra(Intent.EXTRA_EMAIL,from);
                intent.putExtra(Intent.EXTRA_SUBJECT,"this is testing perpose.");
                intent.putExtra(Intent.EXTRA_TEXT,"ok fortunately My massege has been sent");
                intent.setType("message/rfc822");
                Intent chosser=Intent.createChooser(intent,"send email");
                startActivity(chosser);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri imageuri=Uri.parse("android.Resource://intent.mangosoft/drawable/"+R.drawable.ic_action_name);
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM,imageuri);
                Intent chooser =Intent.createChooser(intent,"send image");
                intent.setType("image/*");
                startActivity(chooser);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View view) {
                 Intent intent,chooser;
                 intent=new Intent(Intent.ACTION_VIEW);
                 intent.setData(Uri.parse("geo:19.076,72.877"));
                chooser= Intent.createChooser(intent,"Launch Map");
                startActivity(chooser);
            }
        });
        market.setOnClickListener(new View.OnClickListener() {
                 @Override
                  public void onClick(View view) {

                Intent intent,chooser;
                intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("Market://agorasuperstores.com/"));
                chooser= Intent.createChooser(intent,"Local Market");
                startActivity(chooser);
            }
        });
sdcard.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       File pictures= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String [] listofpicture=pictures.list();
        Uri pictureuri=null;
        ArrayList<Uri> uriList=new ArrayList<Uri>();
        for(String picture:listofpicture){
            pictureuri =Uri.parse("file://"+pictures.toString()+"/"+picture);
            uriList.add(pictureuri);
        }
        Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM,uriList);
        Intent chooser=Intent.createChooser(intent,"send_multiple Images");
        startActivity(chooser);

    }
});
    }
}
