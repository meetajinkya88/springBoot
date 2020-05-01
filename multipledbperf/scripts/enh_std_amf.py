# partnerKey = 0646041100
import uuid
import random
import time

class HeaderRecord(object):

    fields = (('recordKey', 2),
              ('partnerKey', 50),
              ('partnerName', 32),
	      ('extPartnerId', 30),
	      ('creationDateTime', 26)
             )

    def __init__(self):
	self.recordKey=''
	self.partneKey=''
	self.partneName=''
	self.extPartnerId=''
	self.creationDateTime=''        
  
    def __str__(self):
        return ''.join([getattr(self, field_name).ljust(width) 
                        for field_name, width in self.fields])

    def gen_random_string(self, string_length=20):
	 """Returns a random string of length string_length."""
         return ('%06x' % random.randrange(16**string_length)).upper() # Return the random string.


class BatchTrailerRecord(object):

    fields = (('recordKey', 2),
              ('batchRecordCount', 10)
             )

    def __init__(self):
	self.recordKey=''
	self.batchRecordCount=''
	  
    def __str__(self):
        return ''.join([getattr(self, field_name).ljust(width) 
                        for field_name, width in self.fields])

    def gen_random_string(self, string_length=20):
	 """Returns a random string of length string_length."""
         return ('%06x' % random.randrange(16**string_length)).upper() # Return the random string.



class FileTrailerRecord(object):

    fields = (('recordKey', 2),
              ('totalBatches', 10),
              ('totalDetailedRecords', 10)
             )

    def __init__(self):
	self.recordKey=''
	self.totalBatches=''
	self.totalDetailedRecords=''       
  
    def __str__(self):
        return ''.join([getattr(self, field_name).ljust(width) 
                        for field_name, width in self.fields])

    def gen_random_string(self, string_length=20):
	 """Returns a random string of length string_length."""
         return ('%06x' % random.randrange(16**string_length)).upper() # Return the random string.


class DetailedRecord(object):

    fields = (('recordKey', 2),
	      ('partnerKey', 50),
	      ('acctNo', 32),
	      ('custId', 32),
              ('onlineEnrl', 32),
              ('masterAcctId', 32),
              ('issuerStateCode', 2), 
              ('hierLevel1Val', 16),
              ('hierLevel2Val', 16),
              ('hierLevel3Val', 16),
              ('fname', 32),
              ('lname', 32),
              ('ssn', 15),
              ('dob', 8),
              ('email', 100),
              ('phone', 10),
              ('addr1', 50),
              ('addr2', 50),
              ('city', 20),
              ('state', 2),
              ('country', 3),
              ('zip', 10),
              ('currStmtBal', 15),
              ('currBal', 15),
              ('minPymtDue', 15),
              ('pastAmtDue', 15),
              ('pymtDueDate', 8),
              ('billingDate', 8),
              ('acctEligInd', 1),
              ('discEligInd', 1),
              ('cardAcctUsage', 1),
              ('bankAcctUsage', 1),
              ('filler', 108),
              ('cdfCount', 3),
              ('cdf001', 6),
              ('cdf001Content', 64),
              ('cdf002', 6),
              ('cdf002Content', 64),
              ('cdf003', 6),
              ('cdf003Content', 64),
              ('cdf004', 6),
              ('cdf004Content', 64),
              ('cdf005', 6),
              ('cdf005Content', 64),
              ('scdf001', 6),
              ('scdf001Content', 64),
              ('scdf002', 6),
              ('scdf002Content', 64),
              ('scdf003', 6),
              ('scdf003Content', 64)
              )

    def __init__(self):
	self.recordKey=''
	self.partnerKey=''
	self.acctNo=''
	self.custId=''
	self.onlineEnrl=''
	self.masterAcctId=''
	self.issuerStateCode='' 
	self.hierLevel1Val=''
	self.hierLevel2Val=''
	self.hierLevel3Val=''
	self.fname=''
	self.lname=''
	self.ssn=''
	self.dob=''
	self.email=''
	self.phone=''
	self.addr1=''
	self.addr2=''
	self.city=''
	self.state=''
	self.country=''
	self.zip=''
	self.currStmtBal=''
	self.currBal=''
	self.minPymtDue=''
	self.pastAmtDue=''
	self.pymtDueDate=''
	self.billingDate=''
	self.acctEligInd=''
	self.discEligInd=''
	self.cardAcctUsage=''
	self.bankAcctUsage=''
	self.filler=''
	self.cdfCount=''
	self.cdf001=''
	self.cdf001Content=''
	self.cdf002=''
	self.cdf002Content=''
	self.cdf003=''
	self.cdf003Content=''
	self.cdf004=''
	self.cdf004Content=''
	self.cdf005=''
	self.cdf005Content=''
	self.scdf001=''
	self.scdf001Content=''
	self.scdf002=''
	self.scdf002Content=''
	self.scdf003=''
	self.scdf003Content=''
          	

    def __str__(self):
        return ''.join([getattr(self, field_name).ljust(width) 
                        for field_name, width in self.fields])

    def gen_random_string(self, string_length=20):
	 """Returns a random string of length string_length."""
         return ('%06x' % random.randrange(16**string_length)).upper() # Return the random string.

   


h = HeaderRecord()
bh = HeaderRecord()
f = DetailedRecord()
bt = BatchTrailerRecord()
ft = FileTrailerRecord()

totalRecords = int(raw_input("Enter total records which need to generate: "))
partnerKey = raw_input("Enter Partner Key: ")
partnerName = raw_input("Enter Partner Name: ")
fileName = raw_input("Enter File Name: ")
opfile = open(fileName, 'w')

print 'Total records : ' + str(totalRecords)
startTime = time.clock()

print '\nWriting to file...  '


n=totalRecords
rcdString=str(n)
rcdString=rcdString.zfill(9)

#Writing the File Header
h.recordKey='00'
h.partnerKey = partnerKey
h.partnerName = partnerName
h.extPartnerId='ext_partner_id'
h.creationDateTime='20160715162512156'

opfile.write(str(h) + "\n")

#Writing the Batch Header
bh.recordKey='10'
bh.partnerKey= partnerKey
bh.partnerName= partnerName
bh.extPartnerId='ext_partner_id'
bh.creationDateTime='20160715162512156'

opfile.write(str(bh) + "\n")
                                                                                                              
#writing Detailed record
for x in range(0, totalRecords):
	f.recordKey = '21'
	f.partnerKey = partnerKey
	f.acctNo='acctNo_' + f.gen_random_string(20)
	f.custId=str(random.randint(100000000,999999999))
	f.onlineEnrl='onlineEnroll_'+f.gen_random_string(15)
	f.masterAcctId='mast_acct_id_'+f.gen_random_string(15)
	f.issuerStateCode='NY' 
	f.hierLevel1Val='hier_l1_'+f.gen_random_string(8)
	f.hierLevel2Val='hier_l2_'+f.gen_random_string(8)
	f.hierLevel3Val='hier_l3_'+f.gen_random_string(8)
	f.fname='FN'+f.gen_random_string(10)
	f.lname='LN'+f.gen_random_string(10)
	f.ssn='' + str(random.randint(100000000,999999999))
	f.dob='19880424'
	f.email='ajinkyab@gmail.com'
	f.phone='123456789'
	f.addr1='Addr1_' + f.gen_random_string(10)
	f.addr2='Addr2_' + f.gen_random_string(10)
	f.city='CITY_' + f.gen_random_string(6)
	f.state='NJ'
	f.country='USA'
	f.zip=str(random.randint(100000000,999999999))
	f.currStmtBal='+00000000'+str(random.randint(100,999)) + '.' + str(random.randint(10,99))
	f.currBal='+00000000'+str(random.randint(100,999)) + '.' + str(random.randint(10,99))
	f.minPymtDue='+00000000'+str(random.randint(100,999)) + '.' + str(random.randint(10,99))
	f.pastAmtDue='+00000000'+str(random.randint(100,999)) + '.' + str(random.randint(10,99))
	f.pymtDueDate='20160730'
	f.billingDate='20160801'
	f.acctEligInd='E'
	f.discEligInd='Y'
	f.cardAcctUsage='E'
	f.bankAcctUsage='E'
	f.cdfCount='009'
	f.cdf001='cdf001'
	f.cdf001Content='cdf001_'+f.gen_random_string(25)
	f.cdf002='cdf002'
	f.cdf002Content='cdf002_'+f.gen_random_string(25)
	f.cdf003='cdf003'
	f.cdf003Content='cdf003_'+f.gen_random_string(25)
	f.cdf004='cdf004'
	f.cdf004Content='cdf004_'+f.gen_random_string(25)
	f.cdf005='cdf005'
	f.cdf005Content='cdf005_'+f.gen_random_string(25)
	f.scdf001='scdf01'
	f.scdf001Content='scdf01_'+f.gen_random_string(25)
	f.scdf002='scdf02'
	f.scdf002Content='scdf02_'+f.gen_random_string(25)
	f.scdf003='scdf03'
	f.scdf003Content='scdf03_'+f.gen_random_string(25)
	opfile.write(str(f) + "\n")

#writing batch trailer
bt.recordKey='80'
bt.batchRecordCount=str(totalRecords)

opfile.write(str(bt)+ "\n")


#writing file trailer

ft.recordKey='90'
ft.totalBatches='1'
ft.totalDetailedRecords=str(totalRecords)

opfile.write(str(ft)+ "\n")

opfile.close()

print 'DONE!' + ' Elapsed: ' + str(time.clock() - startTime) + 's.\n'


