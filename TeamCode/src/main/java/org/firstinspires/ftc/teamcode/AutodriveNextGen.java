package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Disabled
@Autonomous(name = "AutodriveNextGen", group = "Linear")

public class AutodriveNextGen extends LinearOpMode {
    private DcMotor topLeft;
    private DcMotor topRight;
    private DcMotor bottomLeft;
    private DcMotor bottomRight;
    //private DcMotor ArmMotor;
    //private DcMotor LinkMotor;
    //private DcMotor RotationMotor;
    private DcMotor leadScrew = null;
    final static double ANDYMARK_TICKS_PER_REV = 1440;
    final static double teeth = ANDYMARK_TICKS_PER_REV*9;
    // The "teeth" will always be 12960
    final static double rotation = ANDYMARK_TICKS_PER_REV*3;
    //The "rotation" value is always 4320
    final static double negitaverotation = -ANDYMARK_TICKS_PER_REV*3;
    //The "negativeRotation" will always be -4320
    final static double rotationBackward = ANDYMARK_TICKS_PER_REV*5;
    //The "rotationBackward" will always be 7200

    double leadPower = -.7;
    double left_stick_x = -.5;
    double left_stick_y = -.5;
    double right_stick_x = .5;
    int prevLeftEncoderPos;
    int prevRightEncoderPos;
//
//    long hook = Math.round(inch*3);
//    String hook2 = Long.toString(hook);
//    int Hook = Integer.parseInt(hook2);
//
//            topLeft.setTargetPosition(Hook);
//            topRight.setTargetPosition(Hook);
//            bottomLeft.setTargetPosition(Hook);
//            bottomRight.setTargetPosition(hook);
    //DON'T DELEATE THE COMMENTES LINES ABOVE

    @Override
    public void runOpMode() throws InterruptedException {
        topLeft = hardwareMap.dcMotor.get("topLeft");
        topRight = hardwareMap.dcMotor.get("topRight");
        bottomLeft = hardwareMap.dcMotor.get("bottomLeft");
        bottomRight = hardwareMap.dcMotor.get("bottomRight");
        //ArmMotor = hardwareMap.dcMotor.get("ArmMotor");
       // LinkMotor = hardwareMap.dcMotor.get("LinkMotor");
        // RotationMotor = hardwareMap.dcMotor.get("RotatingMotor");
        leadScrew = hardwareMap.get(DcMotor.class, "lead_screw");
        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leadScrew.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //RotationMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leadScrew.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //RotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //telemetry.addData("Arm Position", "Starting at %7d",
                //ArmMotor.getCurrentPosition());
        //telemetry.addData("Link Position", "Starting at %7d",
               // LinkMotor.getCurrentPosition());
        telemetry.update();
        //ArmMotor.setDirection(DcMotor.Direction.REVERSE);
       // LinkMotor.setDirection(DcMotor.Direction.FORWARD);
        //RotationMotor.setDirection(DcMotor.Direction.FORWARD);
        leadScrew.setDirection(DcMotor.Direction.REVERSE);
        // double a;
        waitForStart();



        while (opModeIsActive()) {

            while (leadScrew.getCurrentPosition() <= teeth) {
                leadScrew.setPower(-leadPower);
                telemetry.addData("leadScrew position", leadScrew.getCurrentPosition());
                telemetry.update();
            }
            leadScrew.setPower(0);


             while (topRight.getCurrentPosition() <= rotation && topLeft.getCurrentPosition() >= -3 && bottomRight.getCurrentPosition() >= -3 && bottomLeft.getCurrentPosition() <=rotation )
            {
                double topLeftSpeed = (left_stick_x - left_stick_y + right_stick_x);// - // +
                double topRightSpeed = (left_stick_x + left_stick_y + right_stick_x);// +// +
                double bottomLeftSpeed = (-left_stick_x + left_stick_y + right_stick_x);// -// -
                double bottomRightSpeed = (left_stick_x + left_stick_y + right_stick_x);// +// -

                telemetry.addData("Top Left Motor Pwr", topLeftSpeed);
                telemetry.addData("Top Right Motor Pwr", topRightSpeed);
                telemetry.addData("Btm Left Motor Pwr", bottomLeftSpeed);
                telemetry.addData("Btm Right Motor Pwr", bottomRightSpeed);
                telemetry.addData("Top Right Position", topRight.getCurrentPosition());
                telemetry.update();

                topLeft.setPower(topLeftSpeed);
                topRight.setPower(topRightSpeed);
                bottomLeft.setPower(bottomLeftSpeed);
                bottomRight.setPower(bottomRightSpeed);

            }
//            topLeft.setPower(0);
//            topRight.setPower(0);
//            bottomLeft.setPower(0);
//            bottomRight.setPower(0);
            telemetry.addData("Top Right Position", topRight.getCurrentPosition());
            telemetry.addData("Bottom Right Position", bottomRight.getCurrentPosition());
            telemetry.update();

            prevLeftEncoderPos = topLeft.getCurrentPosition();
            prevRightEncoderPos = topRight.getCurrentPosition();

            //need to fix below this
            //while rotation <=3

             // if (prevRightEncoderPos<= -200 && prevLeftEncoderPos <= rotation && bottomRight.getCurrentPosition() <=-200 && bottomLeft.getCurrentPosition() <= rotation )
      //while (rotation <= 3) {}

                double topLeftSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
                double topRightSpeed = (left_stick_x - left_stick_y + right_stick_x);//-
                double bottomLeftSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
                double bottomRightSpeed = (left_stick_x - left_stick_y + right_stick_x);//-


                //telemetry.addData("Right JS", -gamepad1.left_stick_y);
                //telemetry.addData("Left JS", gamepad1.left_stick_x);
                telemetry.addData("Top Left Motor Pwr", topLeftSpeed);
                telemetry.addData("Top Right Motor Pwr", topRightSpeed);
                telemetry.addData("Btm Left Motor Pwr", bottomLeftSpeed);
                telemetry.addData("Btm Right Motor Pwr", bottomRightSpeed);
                telemetry.addData("Top Right Position", topRight.getCurrentPosition());
                telemetry.update();

                topLeft.setPower(topLeftSpeed);
                topRight.setPower(topRightSpeed);
                bottomLeft.setPower(bottomLeftSpeed);
                bottomRight.setPower(bottomRightSpeed);
            }

            topLeft.setPower(0);
            topRight.setPower(0);
            bottomLeft.setPower(0);
            bottomRight.setPower(0);
            telemetry.addData("Top Right Position", topRight.getCurrentPosition());
            telemetry.addData("Bottom Right Position", bottomRight.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
