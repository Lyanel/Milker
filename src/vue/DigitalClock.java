package vue;
/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;*/

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import modele.MilkRs;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DigitalClock extends Label {
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(MilkRs.DATE_FORMAT);
	//private static DateFormat dateFormat = new SimpleDateFormat(MilkRs.DATE_FORMAT);

	
	public DigitalClock() {
		bindToTime();
	}

	private void bindToTime() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
											event -> setText(LocalTime.now().format(dateFormat))),
											new KeyFrame(Duration.seconds(1)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		/* old version :
        Timer timer;
        timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						String heure =  dateFormat.format(new GregorianCalendar().getTime());
						timeLabel.setText(heure);
					}
				});
			}
		}, 0,1000);
		*/
		
	}
}