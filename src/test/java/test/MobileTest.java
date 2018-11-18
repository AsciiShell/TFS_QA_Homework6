package test;

import org.junit.Test;
import pages.TinkoffMobileTariffPage;

public class MobileTest extends BaseRunner {
    /*
        @Test
        public void testGoogle() {
            GoogleSearchPage googleSearch = app.google;
            googleSearch.open();
            googleSearch.openSearchResultPage("мобайл тинькофф", "тинькофф мобайл тарифы");
            GoogleResultPage googleResult = app.googleResults;
            TinkoffMobileTariffPage tinkoffMobile = app.tinkoffMobile;
            googleResult.clickSearchResultsByLinkContains(tinkoffMobile.baseUrl);
            googleResult.switchToWindow("Тарифы Тинькофф Мобайл");
            googleResult.switchToMainTab();
            googleResult.closeCurrentTab();
            tinkoffMobile.switchToMainTab();
            tinkoffMobile.assertUrl();
        }
    */
    @Test
    public void testRegion() {
        TinkoffMobileTariffPage tinkoffMobile = app.tinkoffMobile;
        tinkoffMobile.open()
                .setRegion("Москва")
                .assertRegion("Москва")
                .refresh();
        int defaultMoscowPrice = tinkoffMobile.assertRegion("Москва").
                getTotalPrice();
        int maxMoscowPrice = tinkoffMobile.setInternet("Безлимитны")
                .setMinutes("Безлимитны")
                .setModem(true)
                .setUnlimitedSMS(true)
                .getTotalPrice();

        tinkoffMobile.setRegion("Краснодар")
                .assertPriceNotEqual(defaultMoscowPrice)
                .setInternet("Безлимитны")
                .setMinutes("Безлимитны")
                .setModem(true)
                .setUnlimitedSMS(true)
                .assertPriceEqual(maxMoscowPrice);
    }

    @Test
    public void testFree() {
        app.tinkoffMobile.open()
                .setInternet("0 ГБ")
                .setMinutes("0 минут")
                .setUnlimitedMessages(false)
                .setUnlimitedSocialNetworks(false)
                .setUnlimitedMusic(false)
                .setUnlimitedVideo(false)
                .setUnlimitedSMS(false)
                .assertPriceEqual(0);
    }

    @Test
    public void testDownload() {
        app.tinkoffMobileDocument.open()
                .downloadDocument("Удвоим минуты и гигабайты в первый месяц");
    }
}
