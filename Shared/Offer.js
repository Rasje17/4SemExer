class Offer {
    constructor(id, ownerID, offeringBusiness, title, shortDesc, longDesc, contactInfo, applicants) {
        this.id = id;
        this.ownerID = ownerID;
        this.offeringBusiness = offeringBusiness;
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.contactInfo = contactInfo;
        this.applicants = applicants;
    }

    addApplicant(applicantId) {
        if(!(this.applicants.includes(applicantId))) {
            this.applicants.push(applicantId);  
        }
    }
}

module.exports = Offer;