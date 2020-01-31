package com.example.autocylinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        val messageText = findViewById<EditText>(R.id.messageEditText)

        val sendButton = findViewById<Button>(R.id.sendButton)

        val valueText = findViewById<TextView>(R.id.incomingTextView)

        val database = FirebaseDatabase.getInstance()
        val messageRef = database.getReference("message")

        sendButton.setOnClickListener{
            messageRef.setValue(messageText.text.toString())
            Toast.makeText(this, "Sent", Toast.LENGTH_SHORT).show()
        }

        val dataRef = database.getReference("Value")
        dataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val value = dataSnapshot.getValue()
                valueText.text = value.toString()
                Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError){
                Toast.makeText(context, error.toException().toString(), Toast.LENGTH_LONG).show()
            }
        })


    }
}
