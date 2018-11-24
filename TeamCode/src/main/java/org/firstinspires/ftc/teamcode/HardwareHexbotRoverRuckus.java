
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class HardwareHexbotRoverRuckus {

    /* Public OpMode members. */

    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;
    public DcMotor leadScrewMotor = null;
    public DcMotor ArmMotor = null;
    public DcMotor LinkMotor = null;
    public DcMotor BucketMotor = null;
    public Servo BucketServo = null;

    public Servo TeamMarker = null;

    public DigitalChannel LimitSwitchLinkBottom;
    public DigitalChannel LimitSwitchLinkTop;
    public DigitalChannel LimitSwitchLsBottom;

    BNO055IMU imu;
    Orientation angles;
    Telemetry localtelemetry;
    ElapsedTime runtime = new ElapsedTime(); //what are we doing here

    final static double ANDYMARK_TICKS_PER_REV = 1120;

    final static double WormGearRatio = 9;
    final static double TickPerDeg = (ANDYMARK_TICKS_PER_REV * WormGearRatio) / 360;
    final static double rotation = ANDYMARK_TICKS_PER_REV*3;
    final static double WHEEL_DIAMETER = 3.97401575;
    final static double ArmFinalPosition = 210 * TickPerDeg;
    final static double ArmLiftPosition =115 * TickPerDeg;
    final static double ArmHomePosition = 0;
    final static double LinkFinalPosition = -55 * TickPerDeg;
    final static double LinkHomePosition = 0;
    final static double BucketHomePosition = .33;
    final static double COUNTS_PER_INCH = ANDYMARK_TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI);






    HardwareMap hwMap = null;




    public void init(HardwareMap ahwMap, Telemetry telemetry) {

        localtelemetry = telemetry;

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Map tank Motors to hardware
        leftFrontMotor = hwMap.get(DcMotor.class, "left_FrontMotor");
        leftRearMotor = hwMap.get(DcMotor.class, "left_RearMotor");
        rightFrontMotor = hwMap.get(DcMotor.class, "right_FrontMotor");
        rightRearMotor = hwMap.get(DcMotor.class, "right_RearMotor");

        //Map  arm Motors to Hardware
        ArmMotor = hwMap.get(DcMotor.class, "arm_Motor");
        LinkMotor = hwMap.get(DcMotor.class, "link_Motor");
        BucketMotor = hwMap.get(DcMotor.class, "bucket_Motor");

        // Define and Initialize lead screw Motor

        leadScrewMotor = hwMap.get(DcMotor.class, "lead_ScrewMotor");

        // Define and initialize ALL installed servos.
        BucketServo = hwMap.get(Servo.class, "bucket_Servo");


        LimitSwitchLinkBottom = hwMap.get(DigitalChannel.class, "SwitchLinkBottom");
        LimitSwitchLinkTop = hwMap.get(DigitalChannel.class, "SwitchLinkTop");
        LimitSwitchLsBottom = hwMap.get(DigitalChannel.class, "SwitchLsBottom");

        LimitSwitchLinkBottom.setMode(DigitalChannel.Mode.INPUT);
        LimitSwitchLinkTop.setMode(DigitalChannel.Mode.INPUT);
        LimitSwitchLsBottom.setMode(DigitalChannel.Mode.INPUT);

        //adding rev imu (gyro,accelerometer,etc.)
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;

        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        runtime.reset();

        resetMotorsAndEncoders();
        setMotorDirections();
    }
    //----------------------------------------------------------------------------------------------
    // Methods to Reset Motors and Encoders
    //----------------------------------------------------------------------------------------------

    public void setMotorDirections() {

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
        LinkMotor.setDirection(DcMotor.Direction.FORWARD);
        leadScrewMotor.setDirection(DcMotor.Direction.FORWARD);
        BucketMotor.setDirection(DcMotor.Direction.FORWARD);

    }

    public void resetMotorsAndEncoders() {
        resetMotors();
        resetEncoderValues();
    }


    public void resetMotors() {
        leftRearMotor.setPower(0);
        leftFrontMotor.setPower(0);
        rightRearMotor.setPower(0);
        rightFrontMotor.setPower(0);
        BucketMotor.setPower(0);
        LinkMotor.setPower(0);
        ArmMotor.setPower(0);
        leadScrewMotor.setPower(0);
    }

    public void resetEncoderValues() {
        setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void setEncoderMode(DcMotor.RunMode mode) {
        leftRearMotor.setMode(mode);
        leftFrontMotor.setMode(mode);
        rightRearMotor.setMode(mode);
        rightFrontMotor.setMode(mode);
        LinkMotor.setMode(mode);
        ArmMotor.setMode(mode);
        leadScrewMotor.setMode(mode);
    }


    public void resetServo() {
        BucketServo.setPosition(BucketHomePosition);

    }

    //----------------------------------------------------------------------------------------------
    // Methods for Drive Motors

    public void tankDrive2(double drivePower, double robotAngle, double rotPwr, int inches, LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);

        double wheelSpeeds[] = new double[4];
        int counts = (int) Math.round(COUNTS_PER_INCH * inches);
//must set direction first
        setMotorDirections();
//then set position
        leftFrontMotor.setTargetPosition(counts);

//then set the mode
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        runtime.reset();

        while ((leftFrontMotor.getCurrentPosition () < leftFrontMotor.getTargetPosition()) && aStop.opModeIsActive()) {

        }
        resetMotors();
    }//----------------------------------------------------------------------------------------------


    public void tankDrive(double drivePower, double robotAngle, double rotPwr,double duration,LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        runtime.reset();

        while ((runtime.seconds () < duration) && aStop.opModeIsActive()) {

    }
        resetMotors();
    }
    public void tankDrive(double drivePower, double robotAngle, double rotPwr)
    {

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = (drivePower* Math.sin(robotAngle + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =  - (drivePower*  Math.cos(robotAngle + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =  (drivePower* Math.cos(robotAngle + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =  - (drivePower*  Math.sin(robotAngle + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

    }




    public void normalize(double[] wheelSpeeds)

    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

//----------------------------------------------------------------------------------------------
    // Methods for Lead Screw
    //----------------------------------------------------------------------------------------------

    public void leadScrewUp(double distance, double power, double timeout, LinearOpMode aStop ) {
        resetMotorsAndEncoders();
        int tolerance = 50;
        int leadScrewPitch = 2;
        int counts = (int) Math.round(distance/leadScrewPitch * ANDYMARK_TICKS_PER_REV);
//must set direction first
        leadScrewMotor.setDirection(DcMotor.Direction.FORWARD);
//then set position
        leadScrewMotor.setTargetPosition(counts);
//then set the mode
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        runtime.reset();
        leadScrewMotor.setPower(power);

        while (Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance || Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance ) {
            if (runtime.seconds() > timeout || !aStop.opModeIsActive()) {
                break;
            }

            localtelemetry.addData("Current LeadScrew Counts:", (leadScrewMotor.getCurrentPosition()));
            localtelemetry.addData("LeadScrew Power:", leadScrewMotor.getPower());
            localtelemetry.addData("LeadScrew Target Pos:", leadScrewMotor.getTargetPosition());
            localtelemetry.update();
        }
        resetMotorsAndEncoders();
    }
    public void leadScrewDown(double distance, double power, double timeout) {
        resetMotorsAndEncoders();
        int tolerance = 50;
        int leadScrewPitch = 2;
        int counts = (int) Math.round(distance/leadScrewPitch * ANDYMARK_TICKS_PER_REV);
//must set direction first
        leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
        leadScrewMotor.setTargetPosition(-counts);
//then set the mode
        leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
        runtime.reset();
        leadScrewMotor.setPower(power);

        while (Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance || Math.abs(leadScrewMotor.getTargetPosition() - leadScrewMotor.getCurrentPosition())
                > tolerance) {
            if (runtime.seconds() > timeout) {
                break;
            }

            localtelemetry.addData("Current LeadScrew Counts:", (leadScrewMotor.getCurrentPosition()));
            localtelemetry.addData("LeadScrew Power:", leadScrewMotor.getPower());
            localtelemetry.addData("LeadScrew Target Pos:", leadScrewMotor.getTargetPosition());
            localtelemetry.update();
        }
        resetMotorsAndEncoders();
    }}