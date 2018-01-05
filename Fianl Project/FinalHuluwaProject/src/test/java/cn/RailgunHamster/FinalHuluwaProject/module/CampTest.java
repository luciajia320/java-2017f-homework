package cn.RailgunHamster.FinalHuluwaProject.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampTest {
    @Test
    void antagonize() {
        assertEquals(true, Camp.Huluwa.antagonize(Camp.Monster));
        assertEquals(true, Camp.Monster.antagonize(Camp.Huluwa));
        assertEquals(false, Camp.Huluwa.antagonize(Camp.Neutral));
        assertEquals(false, Camp.Huluwa.antagonize(Camp.Neutral));
        assertEquals(false, Camp.Neutral.antagonize(Camp.Neutral));
    }

    @Test
    void friendlyTo() {
        assertEquals(false, Camp.Huluwa.friendlyTo(Camp.Monster));
        assertEquals(false, Camp.Neutral.friendlyTo(Camp.Neutral));
        assertEquals(false, Camp.Neutral.friendlyTo(Camp.Huluwa));
    }

    @Test
    void neutralTo() {
        assertEquals(true, Camp.Huluwa.neutralTo(Camp.Neutral));
        assertEquals(true, Camp.Neutral.neutralTo(Camp.Neutral));
        assertEquals(false, Camp.Huluwa.neutralTo(Camp.Monster));
    }

}