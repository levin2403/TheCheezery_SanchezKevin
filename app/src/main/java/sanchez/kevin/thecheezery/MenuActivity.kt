package sanchez.kevin.thecheezery


import androidx.activity.enableEdgeToEdge
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        var btnCold: Button = findViewById<Button>(R.id.button_cold_drinks)
        var btnHot: Button = findViewById<Button>(R.id.button_hot_drinks)
        var btnSweets: Button = findViewById<Button>(R.id.button_sweets)
        var btnSalties: Button = findViewById<Button>(R.id.button_salties)


        btnCold.setOnClickListener{
            var intent: Intent = Intent(this,ProductsActivity::class.java)
            intent.putExtra("menuType","coldDrinks")
            startActivity(intent)
        }
        btnHot.setOnClickListener{
            var intent: Intent = Intent(this,ProductsActivity::class.java)
            intent.putExtra("menuType","hotDrinks")
            startActivity(intent)
        }
        btnSweets.setOnClickListener{
            var intent: Intent = Intent(this,ProductsActivity::class.java)
            intent.putExtra("menuType","sweets")
            startActivity(intent)
        }
        btnSalties.setOnClickListener{
            var intent: Intent = Intent(this,ProductsActivity::class.java)
            intent.putExtra("menuType","salties")
            startActivity(intent)
        }
    }
}

