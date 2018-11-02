package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Osheen Autonomus", group = "Linear")

public class OsheenAutonomus extends LinearOpMode {
    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    private ElapsedTime runtime = new ElapsedTime();


    double left_stick_x = -.5;
    double left_stick_y = -.5;
    double right_stick_x = .5;
    int prevLeftEncoderPos;
    int prevRightEncoderPos;
//
//    long hook = Math.round(inch*3);
//    String hook2 = Long.toString(hook);
//    int Hook = Integer.parseInt(hook2);

//            robot.leftFrontMotor.setTargetPosition(Hook);
//            robot.rightFrontMotor.setTargetPosition(Hook);
//            robot.leftRearMotor.setTargetPosition(Hook);
//            robot.rightRearMotor.setTargetPosition(hook);
    //DON'T DELEATE THE COMMENTES LINES ABOVE

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            //Lowers robot from lander
           robot.leadScrewUp( 250,-8,15.0);//Should the power be so high?

            //Unhooks robot from lander
            while (robot.rightFrontMotor.getCurrentPosition() <= robot.rotation && robot.leftFrontMotor.getCurrentPosition() >= -3 && robot.rightRearMotor.getCurrentPosition() >= -3 && robot.leftRearMotor.getCurrentPosition() <=robot.rotation )
            {
                double leftFrontSpeed = (left_stick_x - left_stick_y + right_stick_x);// - // +
                double rightFrontSpeed = (left_stick_x + left_stick_y + right_stick_x);// +// +
                double leftRearSpeed = (-left_stick_x + left_stick_y + right_stick_x);// -// -
                double rightRearSpeed = (left_stick_x + left_stick_y + right_stick_x);// +// -

                telemetry.addData("Top Left Motor Pwr", leftFrontSpeed);
                telemetry.addData("Top Right Motor Pwr", rightFrontSpeed);
                telemetry.addData("Btm Left Motor Pwr", leftRearSpeed);
                telemetry.addData("Btm Right Motor Pwr", rightRearSpeed);
                telemetry.addData("Top Right Position", robot.rightFrontMotor.getCurrentPosition());
                telemetry.update();

                robot.leftFrontMotor.setPower(leftFrontSpeed);
                robot.rightFrontMotor.setPower(rightFrontSpeed);
                robot.leftRearMotor.setPower(leftRearSpeed);
                robot.rightRearMotor.setPower(rightRearSpeed);

            }
            robot.leftFrontMotor.setPower(0);
            robot.rightFrontMotor.setPower(0);
            robot.leftRearMotor.setPower(0);
            robot.rightRearMotor.setPower(0);
            telemetry.addData("Right Front Position", robot.rightFrontMotor.getCurrentPosition());
            telemetry.addData("Right Rear Position", robot.rightRearMotor.getCurrentPosition());
            telemetry.update();

            prevLeftEncoderPos = robot.leftFrontMotor.getCurrentPosition();
            prevRightEncoderPos = robot.rightFrontMotor.getCurrentPosition();

            //Move robot back from lander
             if (prevRightEncoderPos<= -200 && prevLeftEncoderPos <= robot.rotation && robot.rightRearMotor.getCurrentPosition() <=-200 && robot.leftRearMotor.getCurrentPosition() <= robot.rotation )
             while (robot.rotation <= 3) {}

            double leftFrontSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
            double rightFrontSpeed = (left_stick_x - left_stick_y + right_stick_x);//-
            double leftRearSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
            double rightRearSpeed = (left_stick_x - left_stick_y + right_stick_x);//-

            telemetry.addData("Top Left Motor Pwr", leftFrontSpeed);
            telemetry.addData("Top Right Motor Pwr", rightFrontSpeed);
            telemetry.addData("Btm Left Motor Pwr", leftRearSpeed);
            telemetry.addData("Btm Right Motor Pwr", rightRearSpeed);
            telemetry.addData("Top Right Position", robot.rightFrontMotor.getCurrentPosition());
            telemetry.update();

            robot.leftFrontMotor.setPower(leftFrontSpeed);
            robot.rightFrontMotor.setPower(rightFrontSpeed);
            robot.leftRearMotor.setPower(leftRearSpeed);
            robot.rightRearMotor.setPower(rightRearSpeed);
        }

        robot.leftFrontMotor.setPower(0);
        robot.rightFrontMotor.setPower(0);
        robot.leftRearMotor.setPower(0);
        robot.rightRearMotor.setPower(0);
        telemetry.addData("Top Right Position", robot.rightFrontMotor.getCurrentPosition());
        telemetry.addData("Bottom Right Position", robot.rightRearMotor.getCurrentPosition());
        telemetry.update();

        idle();
    }
}
