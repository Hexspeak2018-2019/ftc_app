package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "OutreachCode", group = "Linear")

public class OutreachCode extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        robot.resetMotorsAndEncoders();
        robot.setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {
            //Tank Drive with Joystick

            double drivePower  = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle  = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr      = gamepad1.right_stick_x;

            robot.tankDrive(drivePower,robotAngle,rotPwr);

        }
    }
}


