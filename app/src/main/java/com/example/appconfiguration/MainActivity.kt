package com.example.appconfiguration

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appconfiguration.R
import com.example.appconfiguration.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la barra de herramientas
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Necesitamos el controlador de Navegación
        // Primero accedemos al FragmentContentView y casteamos
        // supportFragmentManager conoce a todos los fragmentos y permite transacciones en t. ejecución
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment

        // Todo NavHostFragment tiene asociado un NavController
        navController = navHostFragment.navController

        // Configuramos los destinos de navegación
        // Solo homeFragment será top-level (sin botón de retroceso)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))

        /*
        Necesitamos integración entre la barra de acción y el controlador de navegación
        Aplicamos la configuración del appBarConfiguration y mostramos el botón de navegación.
        Automáticamente, cambiará el título y el botón de navegación de la Toolbar
        */
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configurar el FAB (FloatingActionButton)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Agregar nuevo streamer", Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show()
        }
    }

    // Método que es llamado después de crear la vista del activity (Menú de opciones superior)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú de opciones; esto agrega elementos a la barra de acción si está presente
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /*
    Para controlar los eventos de los items del toolbar
    */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_home -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.toolbar_streamers -> {
                navController.navigate(R.id.streamersFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
    Le dice al NavController que navegue hacia atrás en su pila de navegación,
    siempre que no esté en el destino inicial
    */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}