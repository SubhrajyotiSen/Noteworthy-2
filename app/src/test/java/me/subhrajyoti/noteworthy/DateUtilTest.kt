package me.subhrajyoti.noteworthy

import me.subhrajyoti.noteworthy.utils.DateUtil
import org.junit.Assert
import org.junit.Test
import java.time.ZoneId

class DateUtilTest {

    @Test
    fun `check date formatting`() {
        val timeStamp = 1580663678L * 1000
        val date = "02 Feb 2020 22:44"
        val zoneId = ZoneId.of("Asia/Kolkata")
        Assert.assertEquals(DateUtil.convertTimestampToReadableDateTime(timeStamp, zoneId), date)
    }
}