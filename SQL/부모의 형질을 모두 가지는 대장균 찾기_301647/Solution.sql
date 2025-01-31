SELECT 
    child.id as ID,
    child.genotype as GENOTYPE,
    parent.genotype as PARENT_GENOTYPE
FROM ECOLI_DATA parent
    LEFT JOIN ECOLI_DATA child on parent.id = child.PARENT_ID
WHERE parent.genotype & child.genotype = parent.genotype