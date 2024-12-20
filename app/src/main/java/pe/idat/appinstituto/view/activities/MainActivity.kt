package pe.idat.appinstituto.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.idat.appinstituto.R
import pe.idat.appinstituto.databinding.ActivityMainBinding
import pe.idat.appinstituto.viewmodel.AlumnoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var alumnoViewModel: AlumnoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alumnoViewModel = ViewModelProvider(this)
            .get(AlumnoViewModel::class.java)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.principalFragment,
                R.id.notasFragment,
                R.id.reservaFragment,
                R.id.asistenciaFragment,
                R.id.justificacionFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mostrarInfoLogin()
    }

    private fun mostrarInfoLogin(){
        val tvidalumno : TextView = binding.navView
            .getHeaderView(0)
            .findViewById(R.id.tvheaderid)
        val tvnomape : TextView = binding.navView
            .getHeaderView(0)
            .findViewById(R.id.tvheaderalumno)
        alumnoViewModel.obtener()
            .observe(this, Observer {
                obj ->
                obj?.let {
                    tvidalumno.text = "${obj.id_alumno}"
                    tvnomape.text = "${obj.nombre} ${obj.apellido}"
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        if (idItem == R.id.action_settings){
            startActivity(
                Intent(this,
                    LoginActivity::class.java)
            )
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}