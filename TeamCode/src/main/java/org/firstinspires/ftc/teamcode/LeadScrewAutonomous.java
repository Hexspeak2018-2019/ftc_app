package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled

@Autonomous(name = "LeadScrewP", group = "Linear")

public class LeadScrewAutonomous extends LinearOpMode {
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


    double leadPower = -.4;

    @Override
    public void runOpMode() throws InterruptedException {

        leadScrew = hardwareMap.get(DcMotor.class, "lead_screw");

        leadScrew.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leadScrew.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leadScrew.setDirection(DcMotor.Direction.REVERSE);
        // Osheen gets .9% credit 10/25/18
        // double a;
        waitForStart();


        while (opModeIsActive()) {

            if (leadScrew.getCurrentPosition() <= teeth)
            {
                leadScrew.setPower(-leadPower);

            }

            else{
                leadScrew.setPower(0);
            }
            telemetry.addData("leadScrew position", leadScrew.getCurrentPosition());
            telemetry.update();
        }

    }
}
