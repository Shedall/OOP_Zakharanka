package com.example.travelaroundbelarus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.travelaroundbelarus.models.User;

public class personal_account extends AppCompatActivity {

    Button btnback,btnmap,btnshowinfo,btninfobel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);
        btnback = findViewById(R.id.batton_back);
        btnshowinfo = findViewById(R.id.showinfo);
        btnmap = findViewById(R.id.btnMap);
        btninfobel = findViewById(R.id.btninfoBel);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnshowinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showinfouser();
            }
        });

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmap();
            }
        });

        btninfobel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showinfoBel();
            }
        });
    }

    private void showinfoBel() {
        String bel = "Информация о Беларуси:\n" +
                "Республика Беларусь расположена в восточной части Европы. На западе с ней граничит Польша, на северо–западе – Литва, на севере – Латвия, на северо–востоке и востоке – Россия, на юге – Украина. Беларусь не имеет выхода к морю, но благодаря своему географическому положению является важным торговым и транспортным коридором между Европой и странами СНГ. Территория страны равнинная, но пейзажи настолько разнообразны, что смогут очаровать любого своей простотой и в тоже время изысканной красотой нетронутой природы. Беларусь располагает богатейшими водными ресурсами, насчитывается более 10 тысяч озер и 20,8 тысяч рек. Природа Беларуси уникальна. Здесь можно встретить много редких видов растений и животных. В республике реализуются крупные природоохранные проекты, созданы государственные заповедники и заказники.\n" +
                "\n" +
                "Климат\n" +
                "Климат умеренно–континентальный, с мягкой и влажной зимой и теплым летом. Средняя температура января колеблется от +4 до -4 °C на юго–западе и юге и до -4..-8 °C на северо–востоке. Зимой нередко приключаются оттепели. Наиболее теплое время — с мая по сентябрь, средняя температура воздуха в этот период колеблется от +13 до +19 °C. Самый теплый месяц — июль, самый холодный — январь.\n" +
                "\n" +
                "Денежная единица\n" +
                "Белорусский рубль. Обменять валюту можно в отделениях любых банков или в пунктах обмена, которые можно найти во всех гостиницах, крупных магазинах и супермаркетах. Кредитной картой (Visa и MasterCard) можно расплатиться в гостиницах, ресторанах и магазинах. Банкоматы есть во всех городах Белоруссии. Отделения банков работают с 9:00 до 18:00 все дни недели, кроме воскресенья. На железнодорожном вокзале и в круглосуточных магазинах обменные пункты также работают 24 часа.";
        AlertDialog.Builder dialog = new AlertDialog.Builder(personal_account.this);
        dialog.setTitle("информация о Беларуси");
        dialog.setMessage(bel);
        dialog.setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openmap() {
        //Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_VIEW);
        User user = getIntent().getParcelableExtra("user");
        String userID = getIntent().getStringExtra("userid");
        Intent intent = new Intent(personal_account.this,MapsActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("userid",userID);
        startActivity(intent);
    }

    private void showinfouser() {
        User user = getIntent().getParcelableExtra("user");
        String userID = getIntent().getStringExtra("userid");
        Intent intent = new Intent(personal_account.this, UserInfoactivity.class);
        intent.putExtra("user", user);
        intent.putExtra("userid",userID);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(personal_account.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}