SELECT 
    child.ID as ID,
    child.GENOTYPE as GENOTYPE,
    parent.GENOTYPE as PARENT_GENOTYPE
FROM ECOLI_DATA parent
    LEFT JOIN ECOLI_DATA child on parent.ID = child.PARENT_ID
WHERE parent.GENOTYPE & child.GENOTYPE = parent.GENOTYPE