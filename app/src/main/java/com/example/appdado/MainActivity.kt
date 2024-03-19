package com.example.appdado

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    //Valor limite para começar em 1
    private var ultimoValor = -1

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declaração do botão
        val botao = findViewById<Button>(R.id.btn_jogar)

        //Imagem do dado
        val imagemDado = findViewById<ImageView>(R.id.imagem_dado)

        //DImagem invisivel para mostrar a animação
        imagemDado.isVisible = false

        //Declaração da textView do dado
        val valorDado = findViewById<TextView>(R.id.text_valorDado)

        //Declaração da animação
        val animacaoDado = findViewById<LottieAnimationView>(R.id.animationView)

        //Declarção do text repedito
        val nmrRepetido = findViewById<TextView>(R.id.text_repetido)
        nmrRepetido.isVisible = false

        //Botão de play
        botao.setOnClickListener {
            nmrRepetido.isVisible = false

            //Gerador de numero aleatório
            val numeroDado = (1..6).random()


            //Mostrar imagem quando for X número
            when (numeroDado) {
                1 -> imagemDado.setImageResource(R.drawable.dice1)
                2 -> imagemDado.setImageResource(R.drawable.dice2)
                3 -> imagemDado.setImageResource(R.drawable.dice3)
                4 -> imagemDado.setImageResource(R.drawable.dice4)
                5 -> imagemDado.setImageResource(R.drawable.dice5)
                6 -> imagemDado.setImageResource(R.drawable.dice6)
            }

            //Altera o subtitle apara o numero do dado
            valorDado.text = getString(R.string.valor_dado, numeroDado)

            //Se for um numero repeteido, vai pra outra tela
            if (numeroDado == ultimoValor) {
                //Aparece o text
                nmrRepetido.isVisible = true

                //Text do numero repetido
                nmrRepetido.text = getString(R.string.repeated_number, numeroDado)

            }
            ultimoValor = numeroDado
        }

        //Configuração da animação
        animacaoDado.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                animacaoDado.isVisible = true
                imagemDado.isVisible = false
            }

            override fun onAnimationEnd(animation: Animator) {
                animacaoDado.isVisible = false
                imagemDado.isVisible = true

            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {

            }

        })
    }


}