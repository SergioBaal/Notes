package ru.geekbrains.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Вы уверены, что хотите выйти?")
                .setMessage(" ")
                .setPositiveButton("Да", (dialog, which) -> {
                    showPushNotification();
                    requireActivity().finish();
                }).setNegativeButton("Нет", (dialog, which) -> {
                    showResumeToast();
                }).setNeutralButton("Отмена", (dialog, which) -> {
                    showResumeToast();
                }).show();
    }

    void showResumeToast() {
        Toast.makeText(requireContext(), "Приложение продолжает свою работу.", Toast.LENGTH_LONG).show();
    }


    public final String CHANNEL_ID = "1";

    void showPushNotification() {
        NotificationManager notificationManager =
                (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //Если версия 26 и выше, то мы покажем канал.
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "CHANNEL1",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Это канал для чего-то");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setContentTitle("Внимание!")
                .setContentText("Уходя, проверь, что ты всё сохранил!")
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);


    }


}
