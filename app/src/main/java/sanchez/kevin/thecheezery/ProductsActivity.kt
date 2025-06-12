package sanchez.kevin.thecheezery

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductsActivity : AppCompatActivity() {

    val menu = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val imageView: ImageView = findViewById(R.id.Categoria)

        val menuOption: String? = intent.getStringExtra("menuType")
        agregarProducto(menuOption)

        when (menuOption) {
            "coldDrinks" -> imageView.setImageResource(R.drawable.colddrinks)
            "hotDrinks" -> imageView.setImageResource(R.drawable.hotdrinks)
            "sweets" -> imageView.setImageResource(R.drawable.sweets)
            "salties" -> imageView.setImageResource(R.drawable.salties)
        }

        val listView: ListView = findViewById(R.id.listview)
        val adaptador = AdaptadorProductos(this, menu)
        listView.adapter = adaptador
    }

    fun agregarProducto(option: String?) {
        when (option) {
            "coldDrinks" -> {
                menu.add(Product("Caramel Frap", R.drawable.caramelfrap, "Caramel syrup meets coffee, milk and ice and whipped cream and buttery caramel sauce layer the love on top.", 5.0))
                menu.add(Product("Chocolate Frap", R.drawable.chocolatefrap, "Rich mocha-flavored sauce meets up with chocolaty chips, milk and ice for a blender bash.", 6.0))
                menu.add(Product("Cold Brew", R.drawable.coldbrew, "Created by steeping medium-to-coarse ground coffee in room temperature water for 12 hours or longer.", 3.0))
                menu.add(Product("Matcha Latte", R.drawable.matcha, "Leafy taste of matcha green tea powder with creamy milk and a little sugar for a flavor balance that will leave you feeling ready and raring to go.", 4.0))
                menu.add(Product("Oreo Milkshake", R.drawable.oreomilkshake, "Chocolate ice cream, and oreo cookies. Topped with whipped cream with cocoa and chocolate syrup.", 7.0))
                menu.add(Product("Peanut Milkshake", R.drawable.peanutmilkshake, "Vanilla ice cream, mixed with peanut butter and chocolate.", 7.0))
            }
            "hotDrinks" -> {
                menu.add(Product("Latte", R.drawable.latte, "Coffee drink made with espresso and steamed milk", 6.0))
                menu.add(Product("Hot chocolate", R.drawable.hotchocolate, "Heated drink consisting of shaved chocolate, topped with marshmallows.", 5.0))
                menu.add(Product("Espresso", R.drawable.espresso, "Full-flavored, concentrated form of coffee.", 4.0))
                menu.add(Product("Chai Latte", R.drawable.chailatte, "Spiced tea concentrate with milk", 6.0))
                menu.add(Product("Capuccino", R.drawable.capuccino, "A cappuccino is an espresso-based coffee drink, prepared with steamed foam.", 7.0))
                menu.add(Product("American coffee", R.drawable.americano, "Espresso with hot water", 2.0))
            }
            "sweets" -> {
                menu.add(Product("Blueberry cake", R.drawable.blueberrycake, "Vanilla cake flavor, topped with cheese topping and blueberries.", 6.0))
                menu.add(Product("Chocolate cupcake", R.drawable.chocolatecupcake, "Chocolate cupcakes topped with butter cream and cherries", 3.0))
                menu.add(Product("Lemon tartalette", R.drawable.lemontartalette, "Pastry shell with a lemon flavored filling", 4.0))
                menu.add(Product("Red Velvet cake", R.drawable.redvelvetcake, "Soft, moist, buttery cake topped with an easy cream cheese frosting.", 6.0))
                // Corregido nombre de drawable (asumiendo que 'strawberrycheesecake' es el correcto)
                menu.add(Product("Cherry cheesecake", R.drawable.strawberrycheesecake, "This cherry topped cheesecake is positively creamy and delicious and will be your new favorite dessert.", 7.0))
                menu.add(Product("Tiramisu", R.drawable.tiramisu, "Coffee-flavored Italian dessert", 6.0))
            }
            "salties" -> {
                menu.add(Product("Chicken crepes", R.drawable.chickencrepes, "Fine crepes stuffed with Alfredo chicken, spinach and mushrooms.", 6.0))
                menu.add(Product("Club Sandwich", R.drawable.clubsandwich, "A delicious sandwich served with french fries.", 5.0))
                menu.add(Product("Panini", R.drawable.hampanini, "Sandwich made with Italian bread  served warmed by grilling.", 4.0))
                // Corregido nombre de drawable
                menu.add(Product("Philly cheese steak", R.drawable.phillycheesesteak, "Smothered in grilled onions, green peppers, mushrooms, and Provolone.", 6.0))
                // Corregido nombre de drawable
                menu.add(Product("Nachos", R.drawable.nachos, "Tortilla chips layered with beef and   melted cheddar cheese. Served with fried beans, guacamole, pico de gallo, and sour topping.", 7.0))
            }
            null -> {
                Log.e("ProductsActivity", "menuType fue null! No se agregaron productos.")
                // mostrar un mensaje al usuario para el error
                Toast.makeText(this, "Error: Categoría no especificada.", Toast.LENGTH_LONG).show()
            }
            else -> {
                Log.w("ProductsActivity", "menuType desconocido: $option. No se agregaron productos.")
                // mostrar un mensaje al usuario
                Toast.makeText(this, "Categoría desconocida: $option", Toast.LENGTH_LONG).show()
            }
        }
    }


    private class AdaptadorProductos(
        private val contexto: Context,
        private val productos: ArrayList<Product>
    ) : BaseAdapter() {

        // ViewHolder para mejorar el rendimiento del ListView
        private class ViewHolder {
            lateinit var imagen: ImageView
            lateinit var nombre: TextView
            lateinit var desc: TextView
            lateinit var precio: TextView
        }

        override fun getCount(): Int {
            return productos.size
        }

        override fun getItem(position: Int): Any {
            return productos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vista: View
            val holder: ViewHolder

            if (convertView == null) {
                val inflador = LayoutInflater.from(contexto)
                vista = inflador.inflate(R.layout.product_view, parent, false)

                holder = ViewHolder()
                holder.imagen = vista.findViewById(R.id.producto_img)
                holder.nombre = vista.findViewById(R.id.producto_nombre)
                holder.desc = vista.findViewById(R.id.producto_desc)
                holder.precio = vista.findViewById(R.id.producto_precio)

                vista.tag = holder
            } else {
                vista = convertView
                holder = vista.tag as ViewHolder
            }

            val productoActual = getItem(position) as Product

            holder.imagen.setImageResource(productoActual.image)
            holder.nombre.text = productoActual.name
            holder.desc.text = productoActual.descripcion
            holder.precio.text = "${productoActual.price}"

            return vista
        }
    }
}