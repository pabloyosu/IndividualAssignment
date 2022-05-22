package es.usj.individualassignment

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.usj.individualassignment.databinding.ActivityA2MovieListBinding
import es.usj.individualassignment.databinding.ActivityA3ContactBinding

class A3Contact : AppCompatActivity() {
    private lateinit var binding: ActivityA3ContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityA3ContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btTelefono.setOnClickListener{
            val uri = Uri.parse("tel:+34678678678")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.btCorreo.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "alu.99976@usj.com")
            startActivity(Intent.createChooser(intent, "Send Email"))
        }

        binding.btWeb.setOnClickListener{
            val uri = Uri.parse("http://www.usj.es")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}