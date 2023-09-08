package com.example.finalprokhaled;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    EditText messageInput;
    ImageView sendButton;
    TextView chatTextView;
    String newusername;
    long lastSignInTime = 0; // הזמן האחרון שבו המשתמש התחבר
    long lastSignInTime1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);
        chatTextView = findViewById(R.id.chat_text_view);

        Intent intent = getIntent();
        newusername = intent.getStringExtra("Value");
        lastSignInTime1 = intent.getLongExtra("Lime",0);
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SecondYearActivity
                Intent intent = new Intent(ChatActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        ValueEventListener messagesValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                StringBuilder chatText = new StringBuilder();
                for (com.google.firebase.database.DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    long messageTime = messageSnapshot.child("timestamp").getValue(Long.class);
                    if (messageTime > lastSignInTime1) {
                        String username = messageSnapshot.child("username").getValue(String.class);
                        if (username != null) {
                            String messageText = messageSnapshot.child("message").getValue(String.class);
                            chatText.append(username).append(": ").append(messageText).append("\n");
                        }
                    }
                }
                chatTextView.setText(chatText.toString());

                getSupportActionBar().setTitle("צ'אט עם " + newusername);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ChatActivity.this, "נכשל בטעינת הצ'אט.", Toast.LENGTH_SHORT).show();
            }
        };

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            lastSignInTime = Objects.requireNonNull(currentUser.getMetadata()).getLastSignInTimestamp();
            DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chat");
            chatRef.orderByChild("timestamp")
                    .startAt(lastSignInTime)
                    .addValueEventListener(messagesValueEventListener);
        }
    }

    private void sendMessage() {
        String messageText = messageInput.getText().toString().trim();
        if (!TextUtils.isEmpty(messageText)) {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                long currentTime = System.currentTimeMillis(); // הזמן הנוכחי
                if (lastSignInTime1 > currentTime) {
                    Toast.makeText(this, "שגיאה: הזמן של ההתחברות גדול מהזמן הנוכחי.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userId = currentUser.getUid();
                String userEmail = currentUser.getEmail();
                if (userEmail != null) {
                    DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chat").push();
                    chatRef.child("timestamp").setValue(currentTime);
                    chatRef.child("username").setValue(newusername);
                    chatRef.child("message").setValue(messageText);
                    messageInput.setText("");
                } else {
                    Toast.makeText(this, "שגיאה: כתובת האימייל של המשתמש ריקה.", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "נא להזין הודעה.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        lastSignInTime = System.currentTimeMillis();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
    }
}
