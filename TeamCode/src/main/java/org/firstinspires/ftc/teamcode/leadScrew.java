package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="LeadScrew", group="Linear Opmode")
//@Disabled
public class leadScrew extends LinearOpMode {

    // Declare Motors
    private DcMotor leadScrew = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables
        leadScrew  = hardwareMap.get(DcMotor.class, "lead_screw");

        // Most robots need the motor on one side to be reversed to drive forward
        leadScrew.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start
        waitForStart();

        // run until the end of the match
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leadPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = gamepad1.left_stick_y;
            double turn  = -gamepad1.right_stick_x;
            leadPower    = Range.clip(drive + turn, -1.0, 1.0) ;

            // Send calculated power to wheels
            leadScrew.setPower(leadPower);

            // Show the elapsed game time and wheel power.
            telemetry.update();
        }
    }
}

