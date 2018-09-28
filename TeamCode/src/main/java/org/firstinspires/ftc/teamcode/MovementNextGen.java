package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MovementNextGen", group = "Linear")
public class MovementNextGen extends LinearOpMode
{
    private DcMotor topLeft;
    private DcMotor topRight;
    private DcMotor bottomLeft;
    private DcMotor bottomRight;

    @Override
    public void runOpMode () throws InterruptedException
    {
        topLeft = hardwareMap.dcMotor.get("topLeft");
        topRight = hardwareMap.dcMotor.get("topRight");
        bottomLeft = hardwareMap.dcMotor.get("bottomLeft");
        bottomRight = hardwareMap.dcMotor.get("bottomRight");



        waitForStart();

        while (opModeIsActive())
        {
           float throttle = -gamepad1.left_stick_x;
           float turn = gamepad1.left_stick_y;


            float topLeftSpeed = -(throttle + turn);//+
            float topRightSpeed = throttle - turn;//-
            float bottomLeftSpeed = -(throttle + turn);//+
            float bottomRightSpeed = throttle - turn;//-

            topLeft.setPower(-topLeftSpeed);
            telemetry.addData("Right JS", -gamepad1.right_stick_y);
            telemetry.addData("Left JS", gamepad1.left_stick_x);
            telemetry.update();

            topRight.setPower(topRightSpeed);
            bottomLeft.setPower(-bottomLeftSpeed);
            bottomRight.setPower(bottomRightSpeed);

            idle();
        }
    }

}
