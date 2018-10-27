package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Arm_Test", group = "Practice")
public class Arm_Test extends OpMode {

    DcMotor mainArm;
    DcMotor angleArm;

    @Override
    public void init() {
        mainArm = hardwareMap.dcMotor.get("leftFront");
        angleArm = hardwareMap.dcMotor.get("rightFront");

        mainArm.setDirection(DcMotor.Direction.FORWARD);
        angleArm.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            mainArm.setPower(0.2);
            angleArm.setPower(0.2);
        }

        else if (gamepad1.a) {
            mainArm.setPower(-0.2);
            angleArm.setPower(-0.2);
        }
        else{
            mainArm.setPower(0);
            angleArm.setPower(0);
        }

        telemetry.addData("mainArm",mainArm.getCurrentPosition() );
        telemetry.update();
        telemetry.addData("angleArm",mainArm.getCurrentPosition() );
        telemetry.update();


    }
}