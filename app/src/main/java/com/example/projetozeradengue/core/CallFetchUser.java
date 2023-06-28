//package com.example.projetozeradengue.core;
//
//import android.app.Application;
//import android.content.Context;
//import android.util.Log;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.example.projetozeradengue.controller.ControllerUser;
//import com.example.projetozeradengue.datamodel.UserDataModel;
//import com.example.projetozeradengue.model.User;
//import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//
//import java.text.ParseException;
//import java.util.List;
//import java.util.UUID;
//
//public class CallFetchUser extends Application {
//
//
//    private FirebaseAuth auth = FirebaseAuth.getInstance();
//
//    public User showUserById(Context context, String id) throws ParseException {
//        ControllerUser controllerUser = new ControllerUser(context);
//
//        List<User> usersList = controllerUser.showUser(UserDataModel.TABLE);
//        for (User user : usersList) {
//            if (user.getId().equals(id)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//
//    private void update_User() {
//        ControllerUser controllerUser = new ControllerUser(getApplicationContext());
//        User user = new User();
//
//        if (controllerUser.update(user)) {
//            Toast.makeText(getApplicationContext(), "Usuario " + user.getNameUser() + " alterado com sucesso....", Toast.LENGTH_LONG).show();
//            Log.i(AppUtil.TAG, "Dado alterado");
//        } else {
//            Toast.makeText(getApplicationContext(), "Usuario " + user.getNameUser() + " nao foi possivel alterar....", Toast.LENGTH_LONG).show();
//
//            Log.e(AppUtil.TAG, "Não foi possível alterar");
//        }
//    }
//
//    public String captureData(String fillUsersTable, TextView textView) {
//        final String[] dataCaptured = {""};
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child(AppUtil.REALTIME_DATABASE_USERS).child(auth.getCurrentUser().getUid()).child(fillUsersTable).get()
//                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if (!task.isSuccessful()) {
//                            Log.e("firebase", "Error getting data", task.getException());
//                        } else {
//                            dataCaptured[0] = String.valueOf(task.getResult().getValue());
//                            textView.setText(dataCaptured[0]);
//
//                        }
//                    }
//                });
//
//        return dataCaptured[0];
//
//    }
//
//    public String saveDateInModel(String fillUsersTable) {
//        final String[] dataCaptured = {""};
//        User user = new User();
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child(AppUtil.REALTIME_DATABASE_USERS).child(auth.getCurrentUser().getUid()).child(fillUsersTable).get()
//                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if (!task.isSuccessful()) {
//                            Log.e("firebase", "Error getting data", task.getException());
//                        } else {
//                            dataCaptured[0] = String.valueOf(task.getResult().getValue());
//                            user.setDob(dataCaptured[0]);
//                            user.setEmail(dataCaptured[0]);
//                            user.setNameUser(dataCaptured[0]);
//                            user.setId(dataCaptured[0]);
//
//                        }
//                    }
//                });
//
//        return dataCaptured[0];
//
//    }
//}
//
