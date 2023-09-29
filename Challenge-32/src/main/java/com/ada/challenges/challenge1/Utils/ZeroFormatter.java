package com.ada.challenges.challenge1.Utils;

import com.ada.challenges.challenge1.Constants.Constants;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Data
public class ZeroFormatter {

    public static String formatter(String numberToBeFormatted, Integer paddingSize) {

        return StringUtils.leftPad(numberToBeFormatted, paddingSize, "0");

    }


}
