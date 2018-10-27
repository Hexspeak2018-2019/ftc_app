package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="BucketServo", group="Linear Opmode")
//@Disabled
public class BucketServo extends LinearOpMode {

    // Declare OpMode members.
    private CRServo bucket = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        bucket = hardwareMap.get(CRServo.class, "Bucket");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        double power = 0.5;
        bucket.setPower(power);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            bucket.setPower(power);

            if(gamepad1.x) {
                double ipower = bucket.getPower() + 0.005;
                bucket.setPower(ipower);
            }

            if(gamepad1.y) {
                double dpower = bucket.getPower() - 0.005;
                bucket.setPower(dpower);
            }

        }
    }
}