package com.example.bashkara

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener{
            if(txtA.text.isEmpty() || txtB.text.isEmpty() || txtC.text.isEmpty()){
                mostrarAlertDialog("Error", "Debe indicar todos los valores solicitados")
                limpiarTextos()
            } else {
                calculoRaices()
            }
        }

        btnLimpiar.setOnClickListener{
            limpiarTextos()
        }

    }

    fun calculoRaices(){
        try{
            val a: Double = txtA.text.toString().toDouble()
            val b: Double = txtB.text.toString().toDouble()
            val c: Double = txtC.text.toString().toDouble()
            val paramRaiz: Double = ((b*b)-4*a*c) //debe ser positivo estrictamente
            if(paramRaiz < 0){
                // no hay raies ... mostramos un mensaje informandolo
                txtRaiz1.setText("∄ raíz ∈ ℝ")
                txtRaiz2.setText("∄ raíz ∈ ℝ")
                mostrarAlertDialog("Aviso", "La funcion cuadratica indicada no tiene " +
                        "raices pertenecientes a los reales ( ∄ x ∈ ℝ / f(x)=0 ).")

            }else{
                val raiz: Double = Math.sqrt(paramRaiz)
                val denom = 2 * a
                val bNegativo = -b // -b
                /* Para dejar dos numeros despues de la coma */
                var res1: Double = (bNegativo + raiz) / denom
                res1 = Math.round(res1 * 100.0).toDouble()
                res1 = res1 / 100
                var res2: Double = (bNegativo - raiz) / denom
                res2 = Math.round(res2 * 100.0).toDouble()
                res2 = res2 / 100

                // mostramos los resultados en los textViews
                if (!(res1 == res2)){
                    // resultados distintos (dos raices reales )
                    txtRaiz1.setText("Raiz x1: ${res1.toString()}")
                    txtRaiz2.setText("Raiz x2: ${res2.toString()}")
                }else{
                    // una sola raiz (raiz doble)
                    txtRaiz1.setText("Raiz x: ${res1.toString()}")
                    txtRaiz2.setText("Hay una raiz doble.")
                }

            }
        }catch (e: Exception){
            mostrarAlertDialog("Error.", "Se produjo un error.")
        }
    }

    fun limpiarTextos(){
        // limpiar todos los TextBoxs
        txtA.setText("")
        txtB.setText("")
        txtC.setText("")
        txtRaiz1.setText("Raiz x1: ----")
        txtRaiz2.setText("Raiz x2: ----")
    }

    fun mostrarAlertDialog(titulo: String, contenido: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(contenido)
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                    dialog.dismiss()    // cerramos el dialogo
                })
            .setNegativeButton(android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón cancel pulsado
                    dialog.dismiss()
                })
            .show()
    }


}

