package com.infoshare.myfitwebapp.service;

import com.infoshare.myfitwebapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class CPMService {
    private static final String MALE = "Male";
    private static final String LOW_ACTIVITY = "Low";
    private static final String MEDIUM_ACTIVITY = "Medium";
    private static final String HIGH_ACTIVITY = "High";
    private static final Double FIRST_LEVEL_OF_ACTIVITY = 1.5;
    private static final Double SECOND_LEVEL_OF_ACTIVITY = 1.6;
    private static final Double THIRD_LEVEL_OF_ACTIVITY = 1.8;
    private static final Double FOURTH_LEVEL_OF_ACTIVITY = 1.9;
    private static final Double FIFTH_LEVEL_OF_ACTIVITY = 2.0;
    private static final Double SIXTH_LEVEL_OF_ACTIVITY = 2.2;

    public double calculateBasalMetabolicRate(User user) {
        double bmr;
        if (user.getGender().equals(MALE)) {
            bmr = calculateBasicBasalMetabolicRate(user) + 5;
        } else {
            bmr = calculateBasicBasalMetabolicRate(user) - 161;
        }
        return bmr; // todo
    }

    private double calculateBasicBasalMetabolicRate(User user) {
        return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.calculateAge());
    }


    public double calculateCompleteMetabolism(User user) {
        double CompleteMetabolism;
        if (isFirstLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * FIRST_LEVEL_OF_ACTIVITY;
        } else if (isSecondLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * SECOND_LEVEL_OF_ACTIVITY;
        } else if (isThirdLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * THIRD_LEVEL_OF_ACTIVITY;
        } else if (isFifthLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * FIFTH_LEVEL_OF_ACTIVITY;
        } else if (isFourthLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * FOURTH_LEVEL_OF_ACTIVITY;
        } else if (isSixthLevelOfActivity(user)) {
            CompleteMetabolism = user.getBasalMetabolicRate() * SIXTH_LEVEL_OF_ACTIVITY;
        } else {
            CompleteMetabolism = 0;
        }
        return CompleteMetabolism;
    }

    private boolean isSixthLevelOfActivity(User user) {
        return user.getLevelOfPrivateActivity().equals(HIGH_ACTIVITY) && user.getLevelOfJobActivity().equals(HIGH_ACTIVITY);
    }

    private boolean isFifthLevelOfActivity(User user) {
        return (user.getLevelOfPrivateActivity().equals(MEDIUM_ACTIVITY) && user.getLevelOfJobActivity().equals(HIGH_ACTIVITY)) ||
                (user.getLevelOfPrivateActivity().equals(HIGH_ACTIVITY) && user.getLevelOfJobActivity().equals(MEDIUM_ACTIVITY));
    }

    private boolean isFourthLevelOfActivity(User user) {
        return user.getLevelOfPrivateActivity().equals(MEDIUM_ACTIVITY) && user.getLevelOfJobActivity().equals(MEDIUM_ACTIVITY);
    }

    private boolean isThirdLevelOfActivity(User user) {
        return (user.getLevelOfPrivateActivity().equals(LOW_ACTIVITY) && user.getLevelOfJobActivity().equals(HIGH_ACTIVITY)) ||
                (user.getLevelOfPrivateActivity().equals(HIGH_ACTIVITY) && user.getLevelOfJobActivity().equals(LOW_ACTIVITY));
    }

    private boolean isSecondLevelOfActivity(User user) {
        return (user.getLevelOfPrivateActivity().equals(LOW_ACTIVITY) && user.getLevelOfJobActivity().equals(MEDIUM_ACTIVITY)) ||
                (user.getLevelOfPrivateActivity().equals(MEDIUM_ACTIVITY) && user.getLevelOfJobActivity().equals(LOW_ACTIVITY));
    }

    private boolean isFirstLevelOfActivity(User user) {
        return user.getLevelOfPrivateActivity().equals(LOW_ACTIVITY) && user.getLevelOfJobActivity().equals(LOW_ACTIVITY);
    }

}
