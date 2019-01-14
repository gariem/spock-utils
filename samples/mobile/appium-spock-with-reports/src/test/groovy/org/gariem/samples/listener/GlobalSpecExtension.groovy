package org.gariem.samples.listener

import org.gariem.utils.spock.reports.listener.DefaultTestListener
import org.spockframework.runtime.extension.IGlobalExtension
import org.spockframework.runtime.model.SpecInfo

class GlobalSpecExtension implements IGlobalExtension {

    @Override
    void start() {

    }

    @Override
    void visitSpec(SpecInfo specInfo) {
        specInfo.addListener(new DefaultTestListener())
    }

    @Override
    void stop() {

    }

}
